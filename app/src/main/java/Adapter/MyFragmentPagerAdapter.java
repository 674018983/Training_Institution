package Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import PageFragment.Address_Fragment;
import PageFragment.Go_Fragment;
import PageFragment.Person_Fragment;
import PageFragment.Search_Fragment;

/**
 * Created by XZC on 2017/4/22.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public final int COUNT = 4;
    public static final List<Fragment> mFragmentList= new ArrayList<>();
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(Search_Fragment.newInstance(0));
        mFragmentList.add(Address_Fragment.newInstance(1));
        mFragmentList.add(Go_Fragment.newInstance(2));
        mFragmentList.add(Person_Fragment.newInstance(3));
    }

    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
