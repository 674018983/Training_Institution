package Utils.Okhttp;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by XZC on 2017/4/15.
 */

public class Okhttp {
    static private OkHttpClient mokhttp = new OkHttpClient();
    private Okhttp(){
    }

    public OkHttpClient getokHttpClient(){
        return  mokhttp;
    }


}
