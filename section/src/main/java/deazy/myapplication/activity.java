package deazy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

/**
 * Created by XZC on 2017/5/7.
 */

public class activity extends AppCompatActivity {

    private static final String TAG = "activity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Training_Institution training_institution = new Training_Institution();
        training_institution.setPid("123456");
        if (training_institution.save()) {
            Toast.makeText(this, "存储成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show();
        }
        Training_Institution mDest = DataSupport.find(Training_Institution.class, 1);
        Log.e(TAG, "onCreate: "+training_institution.toString() );
    }
}
