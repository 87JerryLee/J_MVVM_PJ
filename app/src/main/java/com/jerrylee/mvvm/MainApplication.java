package com.jerrylee.mvvm;

import android.app.Application;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 11:40
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
