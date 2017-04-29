package NetWork.Rx_and_Retrofit;

import android.util.Log;

import NetWork.Rx_and_Retrofit.Model.Training_Institution;
import NetWork.Rx_and_Retrofit.Service.ServiceFactory;
import NetWork.Rx_and_Retrofit.Service.Training_Service;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by XZC on 2017/4/15.
 */

public class RX_Retrofit_Impl {

    interface OnRX_RetrofitListener{
        void onCompleted();
        void onError();
        void onNext();
    }
    /**
     * @param name  网站链接后的参数
     * @param onListener 监听事件
     * 不能在这里面执行修改UI 的操作，如果需要，请在监听事件里执行runOnUiThread
     * */
    public void Rx_retrofit(String name, final OnRX_RetrofitListener onListener) {
        Training_Service service = ServiceFactory.createTrainingService(Training_Service.class
                ,Training_Service.SERVICE_ENDPOINT);
//        service.getData(name)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        if(onListener != null){
//                            onListener.onCompleted();
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e("GithubDemo", e.getMessage());
//                        if(onListener != null){
//                            onListener.onError();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        if(onListener != null){
//                            onListener.onNext();
//                        }
//                    }
//                });
    }
}
