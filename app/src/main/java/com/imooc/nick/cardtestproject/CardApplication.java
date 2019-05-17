package com.imooc.nick.cardtestproject;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class CardApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Fresco.initialize(this);
    }


}
