package PageFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/22.
 */

public class Person_Fragment extends Fragment{
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    public static Person_Fragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE,page);
        Person_Fragment fragment = new Person_Fragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fra_person,container,false);
        return v;
    }
}
