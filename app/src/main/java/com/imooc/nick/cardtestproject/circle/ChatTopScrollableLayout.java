package com.imooc.nick.cardtestproject.circle;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.babytree.baf.ui.scrollable.ScrollableContainer;
import com.babytree.baf.ui.scrollable.ScrollableLayout;
import com.imooc.nick.cardtestproject.R;

public class ChatTopScrollableLayout extends ScrollableLayout {

    private View topView;
    private FrameLayout mFrameLayout;


    public ChatTopScrollableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void init(FragmentManager manager) {
        topView = findViewById(R.id.bb_circle_home_cms_column_layout);
        mFrameLayout = findViewById(R.id.bb_circle_home_cms_feeds_layout);
        FragmentTransaction ft = manager.beginTransaction();
        ChatMyTabFragment fragment = new ChatMyTabFragment();
        ft.add(R.id.bb_circle_home_cms_feeds_layout,fragment).commit();

        if (this != null && fragment instanceof ScrollableContainer) {
            ScrollableContainer mScrollableContainer = (ScrollableContainer) fragment;
            this.setTopOffset(0);
            this.getHelper().setCurrentScrollableContainer(mScrollableContainer);
        }

    }

    public void onScroll(int currentY, int maxY) {
        if (this.isInit()) {
//            this.topView.onScroll();
//            this.mFrameLayout.onScroll();
        }

    }


    public boolean isInit() {
        return topView != null && mFrameLayout != null;
    }
}
