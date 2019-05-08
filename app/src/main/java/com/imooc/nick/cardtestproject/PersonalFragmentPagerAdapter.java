package com.imooc.nick.cardtestproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nickgao.com.SlidingTabLayout.PersonalTabModel;


public class PersonalFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Map<Integer, Fragment> classifyFragments = new HashMap<>();
    private List<PersonalTabModel> classifyModels;
    private int currentSelectedPage;

    public PersonalFragmentPagerAdapter(FragmentManager fm, List<PersonalTabModel> classifyModels) {
        super(fm);
        this.classifyModels = classifyModels;
    }

    @Override
    public Fragment getItem(int position) {
        PersonalTabModel model = classifyModels.get(position);
        return PersonalContentFragment.newInstance(model.id, model.type, model.name, position, currentSelectedPage, model.url);
    }

    public void changeCurrentSelectPage(int currentSelectedPage) {
        this.currentSelectedPage = currentSelectedPage;
    }

    @Override
    public int getCount() {
        return classifyModels.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment classifyFragment = (Fragment) super.instantiateItem(container, position);
        classifyFragments.put(position, classifyFragment);
        return classifyFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        classifyFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return classifyModels.get(position).name;
    }

    public Fragment getPositionFragment() {
        Fragment mPositionFragment = getCurrentFragment();
        if (mPositionFragment != null) {
            return mPositionFragment;
        }
        return null;
    }


    /**
     * 获取当前页面的fragment
     *
     * @return
     */
    public Fragment getCurrentFragment() {
        Fragment mCurrentPrimaryItem = null;
        try {
            Field e = FragmentStatePagerAdapter.class.getDeclaredField("mCurrentPrimaryItem");
            e.setAccessible(true);
            Object val = e.get(this);
            mCurrentPrimaryItem = (Fragment) val;
        } catch (Exception var4) {
            var4.printStackTrace();
        }
        return mCurrentPrimaryItem;
    }
}
