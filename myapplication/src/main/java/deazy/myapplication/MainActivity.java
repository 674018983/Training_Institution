package deazy.myapplication;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bt;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                this);
        viewPager.setAdapter(adapter);

        //TabLayout
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        tabLayout.setupWithViewPager(viewPager);
//        bt = (BottomNavigationView) findViewById(R.id.BottomNavigationView);
//        BottomNavigationViewHelper.disableShiftMode(bt);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("啊速度快解放", "onPageScrolled: "+position+":"+ positionOffset+":"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
//                if (menuItem != null) {
//                    menuItem.setChecked(false);
//                } else {
////                    bt.getMenu().getItem(0).setChecked(false);
//                }
//                menuItem = bt.getMenu().getItem(position);
//                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
