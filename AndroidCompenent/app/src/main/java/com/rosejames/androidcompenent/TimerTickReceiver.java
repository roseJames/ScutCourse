package com.rosejames.androidcompenent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rosejames on 16/6/6.
 */
public class TimerTickReceiver extends BroadcastReceiver {
    private static final String TAG = "TimerTickReceiver";
    private SimpleDateFormat mSimpleFormatter;


    @Override
    public void onReceive(Context context, Intent intent) {
        mSimpleFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Log.i(TAG, "Time is:" + mSimpleFormatter.format(new Date(System.currentTimeMillis())));
    }
}
