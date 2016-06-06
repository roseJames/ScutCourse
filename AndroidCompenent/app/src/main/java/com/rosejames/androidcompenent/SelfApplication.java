package com.rosejames.androidcompenent;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

/**
 * Created by rosejames on 16/6/5.
 */
public class SelfApplication extends Application {

    private static final String TAG = "SelfApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.i(TAG, "onTrimMemory");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged");
    }
}
