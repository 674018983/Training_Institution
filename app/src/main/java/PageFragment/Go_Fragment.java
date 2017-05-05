package PageFragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

import Content.Info;
import EventBus.Event;
import EventBus.EventBusUtils;
import EventBus.query;
import EventBus.query_Address;
import EventBus.return_Address;
import Navigation.AMapUtil;
import Navigation.BusResultListAdapter;
import Utils.ToastUtil;
import deazy.myapp.MainActivity;
import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/22.
 */

public class Go_Fragment extends Fragment implements RouteSearch.OnRouteSearchListener ,
        LocationSource,AMapLocationListener,GeocodeSearch.OnGeocodeSearchListener{
    public static final String ARGS_PAGE = "args_page";
    private MapView mapView;
    private AMap aMap;
    private RouteSearch mRouteSearch;
    private BusRouteResult mBusRouteResult;

    private LinearLayout mBusResultLayout;
    private RelativeLayout mHeadLayout;
    private ListView mBusResultList;

    private com.amap.api.maps.LocationSource.OnLocationChangedListener mListener;
    private String addressName;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private GeocodeSearch geocoderSearch;

//    private LatLonPoint mStartPoint = new LatLonPoint(23.269891, 113.210971);//起点，116.335891,39.942295
//    private LatLonPoint mEndPoint = new LatLonPoint(22.540643, 113.934211);//终点，116.481288,39.995576

    private LatLonPoint mStartPoint = null;
    private LatLonPoint mEndPoint = null;

    private String mCurrentCityName = "广州";
    private final int ROUTE_TYPE_BUS = 1;
    private ProgressDialog progDialog = null;// 搜索时进度条

    private int mPage;
    private boolean frist_route  = false;

    public static Go_Fragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        Go_Fragment fragment = new Go_Fragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fra_go, container, false);

        mapView = (MapView) v.findViewById(R.id.route_map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写

        EventBusUtils.register(this);
        EventBusUtils.postSync(new query(Info.COMPANY_ADDRESS));
        if (aMap == null) {
            aMap = mapView.getMap();
        }

        // 设置定位监听
        aMap.setLocationSource(this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);

        geocoderSearch = new GeocodeSearch(getContext());
        geocoderSearch.setOnGeocodeSearchListener(this);


        // name表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode
        getLatlon("广州市天河区科韵路16号");


//        progDialog = new ProgressDialog(this);
//        showDialog();

        init(v);




        mapView.setVisibility(View.GONE);
        mBusResultLayout.setVisibility(View.VISIBLE);

        return v;
    }


    public void onEvent(return_Address event) {

    }
    /**
     * 设置开始标志跟结束标志
     */
    private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.start)));
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.end)));
    }

    /**
     * 初始化AMap对象
     */
    private void init(View view) {
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        registerListener();
        mRouteSearch = new RouteSearch(getContext());
        mRouteSearch.setRouteSearchListener(this);
        mBusResultLayout = (LinearLayout) view.findViewById(R.id.bus_result);
        mBusResultList = (ListView) view.findViewById(R.id.bus_result_list);
//        mHeadLayout = (RelativeLayout) view.findViewById(R.id.routemap_header);
//        mHeadLayout.setVisibility(View.GONE);
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtil.show(getContext(), "定位中，稍后再试...");
            frist_route = false;
            return;
        }
        if (mEndPoint == null) {
            ToastUtil.show(getContext(), "终点未设置");
            frist_route = false;
            return;
        }
        //设置标志
        setfromandtoMarker();

        showProgressDialog();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_BUS) {// 公交路径规划
            RouteSearch.BusRouteQuery query = new RouteSearch.BusRouteQuery(fromAndTo, mode,
                    mCurrentCityName, 0);// 第一个参数表示路径规划的起点和终点，第二个参数表示公交查询模式，第三个参数表示公交查询城市区号，第四个参数表示是否计算夜班车，0表示不计算
            mRouteSearch.calculateBusRouteAsyn(query);// 异步路径规划公交模式查询
        }
    }


    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(getContext());
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 注册监听
     */
    private void registerListener() {
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        });
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

            }
        });
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

    }

    /**
     * 响应地理编码
     */
    public void getLatlon(final String name) {
//        showDialog();
        String city_code = "020";
        if(name.startsWith("深圳")){
            city_code = "0755";
            mCurrentCityName = "深圳";

        }
        GeocodeQuery query = new GeocodeQuery(name, city_code);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }


    /**
     * 规划路线结果回调方法
     */
    @Override
    public void onBusRouteSearched(BusRouteResult result, int errorCode) {
        dissmissProgressDialog();
        aMap.clear();// 清理地图上的所有覆盖物
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mBusRouteResult = result;
                    BusResultListAdapter mBusResultListAdapter = new BusResultListAdapter(getContext(), mBusRouteResult);
                    mBusResultList.setAdapter(mBusResultListAdapter);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtil.show(getContext(), R.string.no_result);
                }
            } else {
                ToastUtil.show(getContext(), R.string.no_result);
            }
        } else {
            ToastUtil.showerror(getContext(), errorCode);
        }
    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {

    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
        mapView.onDestroy();
    }

    /**
     * 定位
     * */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null&&amapLocation != null) {
            if (amapLocation != null
                    &&amapLocation.getErrorCode() == 0) {
                Log.e("你妹妹", "onLocationChanged: "+amapLocation.getLatitude()+";" +amapLocation.getLongitude());
                mStartPoint = new LatLonPoint(amapLocation.getLatitude(),amapLocation.getLongitude());
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                if( !frist_route ) {
                    frist_route = true;
                    searchRouteResult(ROUTE_TYPE_BUS, RouteSearch.BusDefault);
                }
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
                Log.e("AmapErr",errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(getContext());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }



    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }
    /**
     * 目标的经纬度
     * */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
//        dismissDialog();
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getGeocodeAddressList() != null
                    && result.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = result.getGeocodeAddressList().get(0);
//                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
//                        AMapUtil.convertToLatLng(address.getLatLonPoint()), 15));
//                geoMarker.setPosition(AMapUtil.convertToLatLng(address
//                        .getLatLonPoint()));
                addressName = "经纬度值:" + address.getLatLonPoint() + "\n位置描述:"
                        + address.getFormatAddress();
                mEndPoint = new LatLonPoint( address.getLatLonPoint().getLatitude(),address.getLatLonPoint().getLongitude());
//                ToastUtil.show(main2.this, addressName);
                Log.e("你说呢", "onGeocodeSearched: "+ addressName);
            } else {
                ToastUtil.show(getActivity(), R.string.no_result);
            }
        } else {
            ToastUtil.showerror(getActivity(), rCode);
        }
    }
}