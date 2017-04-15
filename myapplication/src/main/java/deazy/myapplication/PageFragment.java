package deazy.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by XZC on 2017/4/11.
 */


    public class PageFragment extends Fragment {
        public static final String ARGS_PAGE = "args_page";
        private int mPage;

        public static PageFragment newInstance(int page) {
            Bundle args = new Bundle();

            args.putInt(ARGS_PAGE, page);
            PageFragment fragment = new PageFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARGS_PAGE);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.layout,container,false);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            textView.setText("第"+mPage+"页");
            return view;
        }
    }

