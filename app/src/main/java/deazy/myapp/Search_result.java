package deazy.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import Adapter.CardAdapter;
import Content.Info;
import UI.Dialog.Navigation_Reminder_Dialog;
import UI.Dialog.Waiting_Dialog;
import NetWork.Rx_and_Retrofit.Model.Training_Institution;
import NetWork.Rx_and_Retrofit.Service.ServiceFactory;
import NetWork.Rx_and_Retrofit.Service.Training_Service;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XZC on 2017/4/26.
 */

public class Search_result extends Activity {

    private RecyclerView mRecyclerView;
    private ImageView mBack;
    private CardAdapter mCardAdapter;
    private Waiting_Dialog waiting_dialog;
    private String Company_name = "5";
    private String Company_address = "装逼路5号";
    private boolean Check_address = false;
    private static final String TAG = "Search_result";
    private Training_Institution navigation_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.act_search_result);
        init();
        showDialog();
        LinkToNet();
    }



    private void init() {
        mBack = (ImageView) findViewById(R.id.back);
        mRecyclerView = (RecyclerView) findViewById(R.id.search_result_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        Company_name = getIntent().getStringExtra("company");
        Company_address = getIntent().getStringExtra("address");
        Log.e(TAG, "init: "+Company_name+":"+Company_address);

        //查看用户点击哪个列表
        mCardAdapter.setOnClickListener(new CardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(Search_result.this, mCardAdapter.getItemInformation(position).toString(), Toast.LENGTH_SHORT).show();
                navigation_information = mCardAdapter.getItemInformation(position);
                Navigation_Reminder_Dialog.newInstance()
                .setClickListener(new Navigation_Reminder_Dialog.onClickListener() {
                    @Override
                    public void cancel() {
                        Navigation_Reminder_Dialog.newInstance().dismiss();
                    }

                    @Override
                    public void ensure() {
                        Navigation_Reminder_Dialog.newInstance().dismiss();
                        //确定时进行导航
                        back_activity(navigation_information);
                    }
                });
                Navigation_Reminder_Dialog.newInstance().show(getFragmentManager(),"hehe");
//
                        ;

            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_activity();
            }
        });
    }
    private void showDialog() {
        waiting_dialog = new Waiting_Dialog(Search_result.this);
        waiting_dialog.show();
    }
    private void dismissDialog(){
        //若activity还存在，就关闭，不存在关闭会报错
//        if()
    }
    /**
     * 连接网络，向服务器请求数据
     * */
    private void LinkToNet() {
        Training_Service service = ServiceFactory.createTrainingService(Training_Service.class,Training_Service.SERVICE_ENDPOINT);
        service.getData(Company_name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Training_Institution>>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG, "onNext: "+"老子成功了1");
                        if(waiting_dialog.isShowing()){
                            waiting_dialog.dismiss();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onNext(List<Training_Institution> s) {
                            Log.e(TAG, "onNext: "+"老子成功了"+s.get(1));


                        if(!s.equals("")&&s!=null) {
//                            s.get(0).getData("")
                            try {
                                for (int i = 0; i < s.size();i++) {
                                    mCardAdapter.addData(s.get(i),Company_name,Check_address);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.e(TAG, "onNext: "+"获取信息失败或者返回信息为空");
                        }

                    }
                });
    }

    //返回后进行导航
    private void back_activity(Training_Institution navigation_information){
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putInt("result", Info.fragment_navigation);
        bundle.putString("result_company",navigation_information.getName().toString());
        bundle.putString("result_address",navigation_information.getAddress().toString());

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }
    //仅仅返回
    private void back_activity(){
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        bundle.putInt("result",Info.fragment_search);

        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

}
