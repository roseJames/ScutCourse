package com.rosejames.androidcompenent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by rosejames on 16/6/6.
 */
public class NetworkReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkReceiver";

    private Context mContext;

    enum NetState {
        STATE_NONE, STATE_WIFI, STATE_MOBILE
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        Log.i(TAG, "Network State is:" + getNetState());
    }

    private NetState getNetState() {
        NetworkInfo networkInfo = ((ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetState.STATE_NONE;
        } else {
            if (networkInfo.isAvailable() && networkInfo.isConnected()) {
                return NetState.STATE_NONE;
            } else {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    return NetState.STATE_MOBILE;
                } else {
                    return NetState.STATE_WIFI;
                }
            }
        }
    }
}
