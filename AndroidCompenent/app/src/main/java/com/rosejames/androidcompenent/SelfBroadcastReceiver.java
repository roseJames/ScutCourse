package com.rosejames.androidcompenent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by rosejames on 16/6/6.
 */
public class SelfBroadcastReceiver extends BroadcastReceiver {


    private static final String TAG = "SelfBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Get Receiver");
    }
}
