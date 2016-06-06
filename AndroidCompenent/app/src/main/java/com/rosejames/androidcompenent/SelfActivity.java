package com.rosejames.androidcompenent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by rosejames on 16/6/5.
 */
public class SelfActivity extends Activity {

    private static final String TAG = "GREP-SelfActivity : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
        Log.i(TAG, TAG + "onCreate");
    }

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
