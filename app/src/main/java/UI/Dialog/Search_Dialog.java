package UI.Dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/17.
 */

public class Search_Dialog extends DialogFragment {

    public static Search_Dialog newIntance(){
        Search_Dialog search_dialog = new Search_Dialog();
        Bundle bundle =new Bundle();
//        bundle.putString();
//        search_dialog.setArguments(bundle);
        return search_dialog;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.search_dialog,container,false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME , R.style.CustomDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        int dialogHeight = (int) (getActivity().getResources().getDisplayMetrics().heightPixels/3);
        int dialogWidth = (int) (getActivity().getResources().getDisplayMetrics().widthPixels/3);
        getDialog().getWindow().setLayout(dialogWidth,dialogHeight);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
