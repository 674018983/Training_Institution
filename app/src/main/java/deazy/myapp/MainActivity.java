package deazy.myapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.MyFragmentPagerAdapter;
import UI.WeiXinButton.Icon;
import UI.WeiXinButton.WeiXinButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    List<Icon> mlist = new ArrayList<Icon>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

        mlist.add(new Icon(0,R.drawable.search,R.drawable.search2));
        mlist.add(new Icon(1,R.drawable.address,R.drawable.address2));
        mlist.add(new Icon(2,R.drawable.go,R.drawable.go2));
        mlist.add(new Icon(3,R.drawable.one,R.drawable.one2));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        WeiXinButton myview= (WeiXinButton) findViewById(R.id.myview);
        myview.ViewPageListener(viewPager);
        myview.add(mlist);
    }
}
