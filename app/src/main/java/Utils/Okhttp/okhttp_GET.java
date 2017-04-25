package Utils.Okhttp;


import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by XZC on 2017/4/15.
 */

public class okhttp_GET {
    private Okhttp mokhttp  ;


    public okhttp_GET(){
        mokhttp = new Okhttp();
        mokhttp.getokHttpClient();
    }

    public interface OnOkhttpListener{
        void onFailure();
        void onResponse(Response response);
    }
    /**
     * @param url  网站链接
     * @param listener 监听事件
     * 不能在这里面执行修改UI 的操作，如果需要，请在监听事件里执行runOnUiThread
     * */
    public void okhttp_GET(String url, final OnOkhttpListener listener){
         Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = mokhttp.getokHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if( listener != null){
                    listener.onFailure();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if( listener != null){
                        listener.onResponse(response);
                    }
                }else{
                    //返回201，2xx，这些
                }
            }
        });
    }
    /**
     * @param url  网站链接
     * @param listener 监听事件
     * @param mlist 需要携带参数的集合
     * 不能在这里面执行修改UI 的操作，如果需要，请在监听事件里执行runOnUiThread
     * */
    public void okhttp_GET(String url, List<key_value> mlist, final OnOkhttpListener listener){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for ( key_value value : mlist ) {
            urlBuilder.addQueryParameter(value.getKey(),value.getValue());
        }
        String mUrl = urlBuilder.build().toString();
        okhttp_GET(mUrl,listener);
    }



}
