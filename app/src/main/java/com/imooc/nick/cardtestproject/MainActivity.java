package com.imooc.nick.cardtestproject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;

import com.imooc.nick.cardtestproject.circle.ChatScrollTestActivity;

import java.util.ArrayList;
import java.util.List;

import nickgao.com.SlidingTabLayout.HomeSlidingTabLayout;
import nickgao.com.SlidingTabLayout.PersonalTabModel;


public class MainActivity extends FragmentActivity {

    HomeSlidingTabLayout news_home_sliding_tab;
    ViewPager news_home_viewpager;
    PersonalFragmentPagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        news_home_viewpager = (ViewPager) findViewById(R.id.news_home_viewpager);

        news_home_sliding_tab = (HomeSlidingTabLayout) findViewById(R.id.news_home_sliding_tab);
        news_home_sliding_tab.setCustomTabView(R.layout.layout_home_classify_tab_item, R.id.homeTab);
        news_home_sliding_tab.setIsDrawDiver(true);
        initTabModel();

        View view = findViewById(R.id.rlHeader);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatScrollTestActivity.startActivity(MainActivity.this);
            }
        });

    }

    private void initTabModel() {
        List<PersonalTabModel> tabModels = new ArrayList<>();
        PersonalTabModel model1 = new PersonalTabModel();
        model1.index = 1;
        model1.name = "动态";
        model1.id = 1;

        PersonalTabModel model2 = new PersonalTabModel();
        model2.name = "帖子";
        model2.index = 2;
        model2.id = 2;

        PersonalTabModel model3 = new PersonalTabModel();
        model3.index = 3;
        model3.name = "回复";
        model3.id = 3;

        PersonalTabModel model4 = new PersonalTabModel();
        model4.index = 4;
        model4.name = "视频";
        model4.id = 4;

        tabModels.add(model1);
        tabModels.add(model2);
        tabModels.add(model3);
        tabModels.add(model4);

        updateViewPager(tabModels);

    }

    private void updateViewPager(List<PersonalTabModel> tabModels) {
        try {
            if (homePagerAdapter == null) {
                homePagerAdapter = new PersonalFragmentPagerAdapter(getSupportFragmentManager(), tabModels);
                news_home_viewpager.setAdapter(homePagerAdapter);
            } else {
                homePagerAdapter.notifyDataSetChanged();
            }

            news_home_sliding_tab.setViewPager(news_home_viewpager);
            news_home_viewpager.setCurrentItem(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
