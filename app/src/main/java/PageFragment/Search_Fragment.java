package PageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import deazy.myapp.R;
import deazy.myapp.Activity.Search_result;

/**
 * Created by XZC on 2017/4/22.
 */

public class Search_Fragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private EditText mInformation;
    private EditText mInformation_name;
    private EditText mInformation_address;
    private Button confirm;
    private TextWatcher textWatcher;
    private static final String TAG = "Search_Fragment";


    public static Search_Fragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE,page);
        Search_Fragment fragment = new Search_Fragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {

                String text = mInformation.getText().toString();
                search_name(text);
                search_address(text);

            }
        };

    }
    /**
     * 查找公司名称
     * @param text 需要查找的文本
     * */
    private void search_name(String text) {
        String delete_Front_text = "";
        //滤掉公司前面的文本
        String[] step1 = text.split("\\[|\\【");
        if(step1.length > 1 ){
            delete_Front_text = step1[ 1 ];
            String[] step2 = delete_Front_text.split("\\]|\\】");
            if( step2[ 0 ] != "智联招聘" && step2[ 0 ] != "前程无忧"){
                mInformation_name.setText(step2[ 0 ]);
            }

        }else{
            if(!select_name(text)){
                String step[] =text.split("科技");
                if( step.length > 1 ){
                    String company_name[] = step[ 0 ].split("\\s+|\\.|\\。|\\;|\\；|\\,|\\，|\\(|\\（|\\[|\\【");
                    if( company_name.length > 1 ){
                        mInformation_name.setText(company_name[company_name.length-1]+"科技");
                    }
                }
            }
        }
    }

    //匹配公司名称
    private boolean select_name(String in_text) {
        String[] step_of_select = in_text.split("公司");
        if(step_of_select.length > 1){
            String[] step1_of_select = step_of_select[ 0 ].split("\\s+|是|广州|深圳");
            if(step1_of_select.length > 1){
                mInformation_name.setText(step1_of_select[step1_of_select.length]);
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    /**
     * @param text 需要查找的文本
     * 查找公司地址
     * */
    private void search_address(String text) {
        String delete_Front_text = "";
        //滤掉地址前面的文本
        String[] step1 = text.split("地址:");
        Log.e(TAG, "search_address: "+step1.length);
        if(step1.length > 1){
            delete_Front_text = step1[ 1 ];

        }else{
            step1 = text.split("地址：");
        }

        if(step1.length > 1){
            delete_Front_text = step1[ 1 ];
        }else{
            String data ="";
            data = select_address("广州",text);
            if(data != null) {
                delete_Front_text = data;
            }else {
                data = select_address("深圳",text);
                if(data != null) {
                    delete_Front_text = data;
                }
            }

        }
        Log.e(TAG, "search_address12: "+delete_Front_text );
        //滤掉地址后面的文本
            //滤掉以。.（( 空格结尾的
            String[] step2 = delete_Front_text.split("\\.|\\。|\\(|\\（|\\s+");
            mInformation_address.setText(step2[0]);

    }

    //当对方不按正规信息来的时候，先搜地方名字，再识别后面有没有区字，如果有就基本确定地址了
    private String select_address(String city,String in_text) {
        String out_text = null;
        String[] step_of_select = in_text.split(city);
        for(int i = 0;i < step_of_select.length ;i++){
            String[] str = step_of_select[ i ].split("区");
            if(!str[ 0 ].equals(step_of_select[ i ])){
                out_text = city+step_of_select[ i ];
                Log.e(TAG, "search_address11: "+out_text );
            }

        }
        return out_text;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fra_search,container,false);

        mInformation = (EditText) v.findViewById(R.id.information);
        mInformation_name = (EditText) v.findViewById(R.id.information_name);
        mInformation_address = (EditText) v.findViewById(R.id.information_address);

        confirm = (Button) v.findViewById(R.id.confirm_and_search);

        mInformation.addTextChangedListener(textWatcher);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_Result_Activity();
            }
        });

        return v;
    }


    private void start_Result_Activity(){
        Intent intent = new Intent(getContext(), Search_result.class);

        Bundle bundle = new Bundle();
        bundle.putString("company",mInformation_name.getText().toString());
        bundle.putString("address",mInformation_address.getText().toString());

        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent,1);
//        startActivity(intent);
    }

}
