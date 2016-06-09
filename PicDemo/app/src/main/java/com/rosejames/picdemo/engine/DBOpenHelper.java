package com.rosejames.picdemo.engine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * create by roseJames
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private DBOpenHelper(Context context) {
        super(context, "picture.db", null, 1);
    }

    private static DBOpenHelper instance;

    public static DBOpenHelper getHelper(Context context) {
        synchronized (DBOpenHelper.class) {
            if (instance == null)
                instance = new DBOpenHelper(context);
            return instance;
        }
    }

    // 数据库第一次被创建的时候 调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 统计表
        db.execSQL("CREATE TABLE IF NOT EXISTS Picture (id integer primary key autoincrement,pic_name TEXT,pic_url TEXT)");
    }

    // 更新已经装载进入用户手机的数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Picture (id integer primary key autoincrement,pic_name TEXT,pic_url TEXT, pic_des TEXT)");
    }
}