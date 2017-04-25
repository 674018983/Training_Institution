package Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import Content.myAppCompatActivity;
import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/20.
 */

public class Identifying_Activity extends myAppCompatActivity {
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private List<EditText> list;
    private int start_Edit_Number = 0 ;
    private static final String TAG = "Identifying_Activity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.act_identifying);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        et1.addTextChangedListener(tw);
        et2.addTextChangedListener(tw);
        et3.addTextChangedListener(tw);
        et4.addTextChangedListener(tw);
        list = new ArrayList<EditText>();
        list.add(et1);
        list.add(et2);
        list.add(et3);
        list.add(et4);
    }


    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            int STR_length = s.toString().length();
            //数量在增加
            if(STR_length > 0){
                for( int i = 0; i < list.size();i ++){
                    if(i != 0 && list.get(i - 1).isFocused()) {

                        list.get(i - 1).clearFocus();
                        list.get(i).requestFocus();
                        break;
                    }
                }

            }else if(STR_length == 0){
                //数量在减少
                for( int i = 0; i < list.size();i ++){
                    if(i != 0 && list.get(i).isFocused()) {

                        list.get(i).clearFocus();
                        list.get(i - 1).requestFocus();
                        break;
                    }
                }

            }



        }

    };

}


