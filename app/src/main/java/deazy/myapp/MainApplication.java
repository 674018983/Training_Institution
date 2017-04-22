package deazy.myapp;

import android.app.Application;

import Content.Crash.CrashHandler;

/**
 * Created by XZC on 2017/4/19.
 */

public class MainApplication extends Application {
    private  static  MainApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    private static MainApplication getsInstance(){
        return sInstance;
    }
}
