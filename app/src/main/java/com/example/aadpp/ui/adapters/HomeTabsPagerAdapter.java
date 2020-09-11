package com.example.aadpp.ui.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.aadpp.ui.learner_leaders.LearningLeaderFragment;
import com.example.aadpp.ui.skill_leaders.SkillLeaderFragment;


public class HomeTabsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES =
            new String[] { "Learning Leaders", "Skill IQ Leaders"};

    private final Context mContext;

    public HomeTabsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return LearningLeaderFragment.newInstance();
            case 1:
                return SkillLeaderFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
//        return mContext.getResources().getString(TAB_TITLES[position]);
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
