package UI.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import Adapter.CardAdapter;
import deazy.myapp.R;

/**
 * Created by XZC on 2017/5/4.
 */

public class Navigation_Reminder_Dialog extends DialogFragment {

    private static Navigation_Reminder_Dialog navigation_reminder_dialog;

    public static Navigation_Reminder_Dialog newInstance(){
        if(navigation_reminder_dialog == null) {
            navigation_reminder_dialog = new Navigation_Reminder_Dialog();
        }

        return navigation_reminder_dialog;
    }

    public interface  onClickListener{
        void cancel();
        void ensure();
    }

    private onClickListener listener;

    public void setClickListener(onClickListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.dialog_navigation_reminder,container,false);
        Button ensure = (Button) v.findViewById(R.id.ensure);
        Button cancel = (Button) v.findViewById(R.id.cancel);

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ensure();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,R.style.CustomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        int dialogHeight = (int)getActivity().getResources().getDisplayMetrics().heightPixels/4;
        int dialogWidth = (int)getActivity().getResources().getDisplayMetrics().widthPixels/2;
        getDialog().getWindow().setLayout(dialogWidth,dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
