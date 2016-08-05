package com.bwf.landz.ui.welcome.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwf.landz.ui.welcome.fragment.GuidFragment;

/**
 * Created by Lizhangfeng on 2016/7/7 0007.
 * Description:
 */
public class GuideAdapter extends FragmentPagerAdapter {

    public GuideAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GuidFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
