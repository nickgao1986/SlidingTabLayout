package com.imooc.nick.cardtestproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.imooc.nick.cardtestproject.Util.ListFooterUtil;
import com.imooc.nick.cardtestproject.fragment.PersonalDynamicFragment;
import com.imooc.nick.cardtestproject.view.TipView;

public abstract class PersonalContentFragment extends Fragment {

    private View rootView;
    private TextView content;
    protected ListView mListview;
    protected TipView loadingView;
    public Activity mActivity;
    protected View footer;
    public boolean bLoading = false;//是不是在加载

    public enum LoadingState {
        LOADING_NEW_DATA,LOADING_MORE,NO_DATA,LOADING_COMPLETE,NO_NETWORK,FOOTER_COMPLETE,NETWORK_ERROR,PULL_BLACK
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity)context;
    }

    public static Fragment newInstance(int id, int type, String name, int position, int currentSelectedPage, String url) {
        PersonalContentFragment classifyFragment = new PersonalDynamicFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("classifyId", id);
        bundle.putInt("classifyType", type);
        bundle.putString("classifyName", name);
        bundle.putInt("position", position);
        bundle.putInt("currentSelectedPage", currentSelectedPage);
        bundle.putString("url", url);
        classifyFragment.setArguments(bundle);

        return classifyFragment;
    }

    protected abstract void sendRequest();
    public abstract void subClassLoadMore();


    private void initView() {
        mListview = (ListView) rootView.findViewById(R.id.news_home_listview);

        footer = ListFooterUtil.getInstance().getListViewFooter(mActivity.getLayoutInflater());
        mListview.addFooterView(footer);

        loadingView = (TipView) rootView.findViewById(R.id.news_home_loadingView);
        loadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                //没在滚动，并且到底，触发获取更多
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && !bLoading && view.getLastVisiblePosition() != 0 && view.getLastVisiblePosition() == (view.getCount() - 1)) {
                    loadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

    }


    protected void loadMore() {
        if (bLoading)
            return;
        bLoading = true;
        subClassLoadMore();
    }

    public void setLoadingState(LoadingState state) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String name = getArguments().getString("classifyName");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_personal_content_fragment, null);
        return rootView;
    }
}
