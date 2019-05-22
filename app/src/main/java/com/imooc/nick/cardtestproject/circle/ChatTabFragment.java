package com.imooc.nick.cardtestproject.circle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babytree.baf.ui.recyclerview.RecyclerBaseView;
import com.babytree.baf.ui.scrollable.OnScrollStateChangeListener;
import com.babytree.baf.ui.scrollable.ScrollableContainer;
import com.imooc.nick.cardtestproject.R;

public class ChatTabFragment extends Fragment implements ScrollableContainer {

    private RecyclerBaseView mRecyclerBaseView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerBaseView = view.findViewById(R.id.rv_tab);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_my_favorite_tab_pop1,container,false);
    }





    public static ChatTabFragment newInstance(String tabId, String title) {
        ChatTabFragment fragment = new ChatTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tabId", tabId);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }


//    @Override
//    public void success(BbApiBase api, JSONObject response) {
//        ChatCategoryGroupApi listApi = (ChatCategoryGroupApi) api;
//        handleListItems(listApi.getCategoryGroupBeanList());
//    }
//
//
//    @Override
//    public BbPhpApiBase getApi() {
//        return new ChatCategoryGroupApi("5", mCurrentPage);
//    }
//
//    @Override
//    public RecyclerBaseAdapter getAdapter() {
//        mChatClassifyListAdapter =  new ChatClassifyListAdapter(mContext, "怀孕", "5");
//        return mChatClassifyListAdapter;
//    }

    @Override
    public View getScrollableView() {
        return mRecyclerBaseView;
    }

    @Override
    public ViewPager getViewPager() {
        return null;
    }

    @Override
    public void scrollToTop() {

    }

    @Override
    public void setCanScroll(boolean canScroll) {
//        if (mBabytreeRefreshRecyclerView != null) {
//            mBabytreeRefreshRecyclerView.setCanScrollVertically(canScroll);
//        }
    }

    @Override
    public void setOnScrollStateChangeListener(OnScrollStateChangeListener onScrollStateChangeListener) {

    }


}
