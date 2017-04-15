package Content;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import Content.Inject.DaggermainComponent;
import EventBus.EventBusUtils;
import UI.LogUtils.logUtil;
/**
 * Created by XZC on 2017/4/7.
 */

public class myAppCompatActivity extends AppCompatActivity {
    @Inject
    logUtil mLog;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        DaggermainComponent.builder()
                .build()
                .inject(this);
//        mLog.Error("Testing");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBusUtils.register();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
