package com.imooc.nick.cardtestproject.Util;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imooc.nick.cardtestproject.R;

public class ListFooterUtil {
    private static ListFooterUtil instance;
    private int height = 0;

    public static ListFooterUtil getInstance() {
        if (instance == null) {
            instance = new ListFooterUtil();
        }
        return instance;
    }

    /**
     * 创建footerview
     *
     * @param layoutInflater
     * @return
     */
    public View getListViewFooter(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.layout_list_footer_load_item, null);
    }

    /**
     * 隐藏底部
     *
     * @param footerView
     */
    public void hideListViewFooter(View footerView) {
        LinearLayout.LayoutParams lp = getLayoutParams(footerView);
        if (lp == null)
            return;
        lp.height = 0;
        setLayoutParams(footerView, lp);
    }


    /**
     * 显示底部
     *
     * @param footerView
     */
    private void showListViewFooter(View footerView) {
        LinearLayout.LayoutParams lp = getLayoutParams(footerView);
        if (lp == null)
            return;
        if (height == 0) {
            height = DeviceUtils.dip2px(footerView.getContext(), 55);
        }
        lp.height = height;
        setLayoutParams(footerView, lp);
    }

    private LinearLayout.LayoutParams getLayoutParams(View footerView) {
        LinearLayout.LayoutParams lp = null;
        if (footerView != null) {
            View contentView = footerView.findViewById(R.id.linearTop);
            if (contentView != null) {
                lp = (LinearLayout.LayoutParams) contentView.getLayoutParams();
                if (lp == null)
                    lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
            }
        }
        return lp;
    }

    private void setLayoutParams(View footerView, LinearLayout.LayoutParams lp) {
        try {
            footerView.findViewById(R.id.linearTop).setLayoutParams(lp);
        } finally {

        }
    }

    /**
     * 更新view显示
     *
     * @param footerView
     * @param state
     * @param text
     */
    public void updateListViewFooter(View footerView, ListViewFooterState state, String text) {
        try {
            if (footerView == null)
                return;
            TextView tvMoreText = (TextView) footerView.findViewById(R.id.load_more);
            ProgressBar progressBar = (ProgressBar) footerView.findViewById(R.id.pull_to_refresh_progress);
            showListViewFooter(footerView);
            tvMoreText.setVisibility(View.VISIBLE);
            if (state == ListViewFooterState.NORMAL) {
                tvMoreText.setText("");
                progressBar.setVisibility(View.INVISIBLE);
            } else if (state == ListViewFooterState.LOADING) {
                if (TextUtils.isEmpty(text))
                    tvMoreText.setText("正在加载更多...");
                else
                    tvMoreText.setText(text);
                progressBar.setVisibility(View.VISIBLE);
            } else if (state == ListViewFooterState.COMPLETE) {
                if (TextUtils.isEmpty(text))
                    tvMoreText.setText("没有更多数据啦~");
                else
                    tvMoreText.setText(text);
                progressBar.setVisibility(View.INVISIBLE);
            } else if (state == ListViewFooterState.ERROR) {
                if (TextUtils.isEmpty(text))
                    tvMoreText.setText("加载失败！");
                else
                    tvMoreText.setText(text);
                progressBar.setVisibility(View.INVISIBLE);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public enum ListViewFooterState {
        NORMAL, LOADING, COMPLETE, ERROR
    }

    public TextView getTextView(View footerView){
        return (TextView) footerView.findViewById(R.id.load_more);
    }

    public LinearLayout getContainer(View footerView){
        return (LinearLayout) footerView.findViewById(R.id.linearTop);
    }
}
