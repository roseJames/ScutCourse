package com.rosejames.picdemo.config;

import android.app.Application;

/**
 * Created by rosejames on 16/6/7.
 */
public class PicDemoApplication extends Application {

    private static PicDemoApplication mPicDemoApplication;

    public static Application getApplication() {
        return mPicDemoApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPicDemoApplication = this;
        FilePath.init();
    }
}
