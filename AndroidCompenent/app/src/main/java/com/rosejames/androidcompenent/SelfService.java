package com.rosejames.androidcompenent;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/5.
 */
public class SelfService extends Service {

    private static final String TAG = "GREP-SelfService : ";

    @Nullable
    @Override
    public SelfBinder onBind(Intent intent) {
        Log.i(TAG, TAG + "onBind");
        return new SelfBinder().setMessage("I am SelfService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, TAG + "onCreate");
        mWatchDog.start();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, TAG + "onStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, TAG + "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + "onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, TAG + "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, TAG + "onRebind");
    }

    private Thread mWatchDog = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, TAG + (isServiceAlive() ? "Service Alive" : "Service dead"));
            }
        }
    };

    private boolean isServiceAlive() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>)
                activityManager.getRunningServices(Integer.MAX_VALUE);
        for (int i = 0; i < runningService.size(); i++) {
            if (runningService.get(i).service.getClassName().toString().equals(SelfService.class.getName())) {
                return true;
            }
        }
        return false;
    }


}
