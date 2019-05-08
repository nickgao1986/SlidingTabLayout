package com.imooc.nick.cardtestproject;

import android.app.Application;
import android.content.Context;

public class CardApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


}
