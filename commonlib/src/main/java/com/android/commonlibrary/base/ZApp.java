package com.android.commonlibrary.base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

public class ZApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
    }


    private void initRouter() {

        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }

}
