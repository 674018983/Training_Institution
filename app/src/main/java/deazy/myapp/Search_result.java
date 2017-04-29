package deazy.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.util.List;

import javax.security.auth.login.LoginException;

import Adapter.CardAdapter;
import Navigation_UI.Dialog.Waiting_Dialog;
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
    private String Company_name = "58";
    private static final String TAG = "Search_result";

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


    }
    private void showDialog() {
        waiting_dialog = new Waiting_Dialog(Search_result.this);
        waiting_dialog.show();
    }
    private void dismissDialog(){
        //若activity还存在，就关闭，不存在关闭会报错
//        if()
    }

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
                                    mCardAdapter.addData(s.get(i));
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

}
