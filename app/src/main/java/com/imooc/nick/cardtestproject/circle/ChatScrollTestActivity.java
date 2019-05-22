package com.imooc.nick.cardtestproject.circle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.imooc.nick.cardtestproject.R;


public class ChatScrollTestActivity extends FragmentActivity implements View.OnClickListener, ChatScrollableLayout.OnScrollListener {

    private static final Handler mHandler = new Handler(Looper.getMainLooper());

    private ChatScrollableRefreshLayout mRefreshLayout;
    private ChatScrollableLayout mScrollableLayout;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ChatScrollTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_scoll_fragment);
        mRefreshLayout = findViewById(R.id.bb_circle_home_cms_scrollable_layout);
        mScrollableLayout = mRefreshLayout.getRefreshableView();
        mScrollableLayout.init(getSupportFragmentManager());
    }

    @Override
    public void onClick(View v) {

    }



    @Override
    public void onScroll(int currentY, int maxY) {
        if (mScrollableLayout != null) {
            mScrollableLayout.onScroll(currentY, maxY);
        }
        if (mScrollableLayout != null && mScrollableLayout.isSticked()) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   // setIconAlphaAnim(false);
                }
            }, 50);
        } else {
            //setIconAlphaAnim(true);
        }
    }

    @Override
    public void onScrollStop() {

    }
}
