package com.imooc.nick.cardtestproject.circle;

import android.content.Context;
import android.util.AttributeSet;

import com.babytree.baf.ui.scrollable.BaseRefreshScrollLayout;
import com.imooc.nick.cardtestproject.R;

public class ChatScrollableRefreshLayout extends BaseRefreshScrollLayout<ChatScrollableLayout> {

    public ChatScrollableRefreshLayout(Context context) {
        super(context);
    }

    public ChatScrollableRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.chat_circle_scroll_layout;
    }

}
