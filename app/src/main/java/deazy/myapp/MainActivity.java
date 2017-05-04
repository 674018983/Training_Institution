package deazy.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Switch;

import Adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import Content.Info;
import UI.WeiXinButton.Icon;
import UI.WeiXinButton.WeiXinButton;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case  1:
                if(resultCode == RESULT_OK){
                    int CurrentItem = data.getIntExtra("result",Info.fragment_nothing);
                    switch (CurrentItem){
                        case Info.fragment_search:
                            viewPager.setCurrentItem(CurrentItem);
                            break;
                        case Info.fragment_location:
                            break;
                        case Info.fragment_navigation:
//                            String company_name = data.getStringExtra("result_company");
                            String company_address = data.getStringExtra("result_address");
                            viewPager.setCurrentItem(CurrentItem);
                            break;
                        case Info.fragment_person:
                            break;
                        default:
                            break;
                    }

                }
                break;
            default:
                break;
        }
    }
}
