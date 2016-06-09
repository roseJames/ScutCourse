package com.rosejames.picdemo.engine;

import android.content.Context;
import android.content.SharedPreferences;

import com.rosejames.picdemo.config.PicDemoApplication;

/**
 * Created by rosejames on 16/6/7.
 */
public class SPCenter {

    private static SharedPreferences sSharedPreferences;
    private static final String SP_NAME = "setting.sp";

    public static SharedPreferences getSharedPreferences() {
        if (sSharedPreferences == null) {
            sSharedPreferences = PicDemoApplication.getApplication().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sSharedPreferences;
    }

    public static boolean putString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        return editor.commit();
    }


    public static boolean putLong(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        return editor.commit();
    }


}
