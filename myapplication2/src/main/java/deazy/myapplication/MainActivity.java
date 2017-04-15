package deazy.myapplication;

import android.graphics.drawable.*;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import deazy.mylibrary.mweixingroup;
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    List<Icon> mlist = new ArrayList<Icon>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlist.add(new Icon(0,R.drawable.search,R.drawable.search2));
        mlist.add(new Icon(1,R.drawable.address,R.drawable.address2));
        mlist.add(new Icon(2,R.drawable.go,R.drawable.go2));
        mlist.add(new Icon(3,R.drawable.one,R.drawable.one2));

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        WeiXinButton myview= (deazy.myapplication.WeiXinButton) findViewById(R.id.myview);
        myview.ViewPageListener(viewPager);
        myview.add(mlist);
    }
}
