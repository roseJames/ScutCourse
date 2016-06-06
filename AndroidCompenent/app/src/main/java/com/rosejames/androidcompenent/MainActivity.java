package com.rosejames.androidcompenent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    private static final String TAG = "GREP-MainActivity : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_new_activity).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelfActivity.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, TAG + "onCreate");

        findViewById(R.id.btn_new_service_bind).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                dotoBindService();
            }
        });

        findViewById(R.id.btn_new_service_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dotoStartService();
            }
        });

        findViewById(R.id.btn_unbind_service).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                dotoUnBindService();
            }
        });

        findViewById(R.id.btn_stop_service).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                dotoStopService();
            }
        });
        findViewById(R.id.btn_receiver_time).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                dotoRegisterTimerReceiver();
            }
        });
        findViewById(R.id.btn_send_broadcast).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dotoSendBroadcast();
            }


        });
    }

    private void dotoSendBroadcast() {
        Intent intent = new Intent(SelfBroadcastReceiver.class.getName());
        sendBroadcast(intent);
    }

    private void dotoStopService() {
        Intent intent = new Intent(this, SelfService.class);
        stopService(intent);
    }

    private void dotoUnBindService() {
        try {
            unbindService(mServiceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private TimerTickReceiver mTimerTickReceiver;

    private void dotoRegisterTimerReceiver() {
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        mTimerTickReceiver = new TimerTickReceiver();
        registerReceiver(mTimerTickReceiver, intentFilter);
    }

    private void dotoStartService() {
        Intent intent = new Intent(this, SelfService.class);
        startService(intent);
    }

    private void dotoBindService() {
        Intent intent = new Intent(this, SelfService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder instanceof SelfBinder) {
                Log.i(TAG, TAG + "onServiceConnected getMessage:" + ((SelfBinder) iBinder).getMessage());
            }
            Log.i(TAG, TAG + "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, TAG + "onServiceDisconnected");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, TAG + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, TAG + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, TAG + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, TAG + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + "onDestroy");
        try {
            unregisterReceiver(mTimerTickReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, TAG + "finish");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, TAG + "onRestart");
    }
}
