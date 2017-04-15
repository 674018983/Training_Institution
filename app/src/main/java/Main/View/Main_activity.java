package Main.View;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import Content.myAppCompatActivity;
import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/13.
 */

public class Main_activity  extends myAppCompatActivity  implements main_View{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main_layout);
    }

}
