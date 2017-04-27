package Navigation_UI.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/26.
 */

public class Waiting_Dialog extends Dialog {
    Context mContext;
    public Waiting_Dialog(Context context) {
        super(context,R.style.dialog);
        mContext = context;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =inflater.inflate(R.layout.dialog_waiting,null);
        this.setContentView(v);
    }
}
