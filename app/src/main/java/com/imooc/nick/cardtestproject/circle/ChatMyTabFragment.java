package com.imooc.nick.cardtestproject.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babytree.baf.ui.common.BAFFragmentAdapter;
import com.babytree.baf.ui.common.BAFViewPager;
import com.babytree.baf.ui.tab.TabLayout;
import com.imooc.nick.cardtestproject.R;

import java.util.ArrayList;
import java.util.List;

public class ChatMyTabFragment extends Fragment implements ViewPager.OnPageChangeListener{

    private TabLayout mTabLayout;
    private BAFViewPager mViewPager;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.cms_circle_tab_fragment_list_tabs);
        mTabLayout.setSelectedIndicatorFitText(true);
        mViewPager = view.findViewById(R.id.cms_circle_tab_fragment_list_viewpager);
        mViewPager.addOnPageChangeListener(this);

        List<ChatTabFragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(ChatTabFragment.newInstance("",""));
        mFragmentList.add(ChatTabFragment.newInstance("",""));
        List<String> mFragmentTitleList = new ArrayList<>();
        mFragmentTitleList.add("aa");
        mFragmentTitleList.add("bb");
        BAFFragmentAdapter adapter = new BAFFragmentAdapter<>(getChildFragmentManager(), mFragmentList, mFragmentTitleList);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.circle_tab_fragment,container,false);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}