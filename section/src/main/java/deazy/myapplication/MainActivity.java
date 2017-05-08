package deazy.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //要显示的列表数据，未按字母顺序排列
    final String[] strings = {"ooo","abb","zzz","ppp","bcc","ppq","eee","eff","fgg","sss","ghh","hhh","iii","vvv",
            "jkk","jkl","kkk","yyy","lll","mmm","nnn","aaa","bbb","bdd","qqq","qrr","rrr","ggg","srr","ttt","tfg","uuu",
            "jjj","www","www","wwe","wwg","xxt","xxx","kin","acc","was","wtg","wfg","brg","hqq"};

    ListView mListView;
    TextView maskTv;//显示在屏幕中央的字母浮层
    SideBar mSideBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initData();
    }

    //初始化列表数据
    private List<String> initList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            stringList.add(strings[i]);
        }
        //对列表数据进行按字母排序
        Collections.sort(stringList);
        return stringList;
    }

    private void initData() {
        MyAdapter adapter = new MyAdapter(this, initList());
        mListView.setAdapter(adapter);
        mSideBar.setListView(mListView);
        mSideBar.setOnTouchChangedListener(new SideBar.OnTouchChangedListener() {
            @Override
            public void onTouchDown(char c) {
                maskTv.setVisibility(View.VISIBLE);
                maskTv.setText(c+"");
            }
            @Override
            public void onTouchUp() {
                maskTv.setVisibility(View.GONE);
            }
        });
    }

    private void findViews() {
        mListView = (ListView) findViewById(R.id.main_lv);
        maskTv = (TextView) findViewById(R.id.main_mask_tv);
        mSideBar = (SideBar) findViewById(R.id.main_sb);
    }

}
