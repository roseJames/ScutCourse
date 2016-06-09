package com.rosejames.picdemo.engine;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rosejames.picdemo.config.PicDemoApplication;
import com.rosejames.picdemo.model.PicModel;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/8.
 */
public class PictureDao {

    DBOpenHelper mDBOpenHelper;

    public static final String PIC_NAME = "pic_name";
    public static final String PIC_URL = "pic_url";
    public static final String TABLE_NAME = "Picture";

    public PictureDao() {
        mDBOpenHelper = DBOpenHelper.getHelper(PicDemoApplication.getApplication());
    }

    /**
     * 添加一条记录，直接使用API对数据库进行操作，其实就是把SQL语句封装，使用append拼接，加快开发效率
     */
    public synchronized void add(String picName, String picUrl) {
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(PIC_NAME, picName);
            values.put(PIC_URL, picUrl);
            db.insert(TABLE_NAME, null, values);
        }
    }

    public ArrayList<PicModel> findAllMessage() {
        ArrayList<PicModel> picModels = new ArrayList<>();
        SQLiteDatabase db = mDBOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                PicModel picModel = new PicModel();
                int index = cursor.getColumnIndex(PIC_URL);
                picModel.setPicUrl(cursor.getString(index));
                picModel.setPicName(cursor.getString(cursor.getColumnIndex(PIC_NAME)));
                picModels.add(picModel);
            }
            cursor.close();
        }
        return picModels;
    }

    // 新的下载数据库删除数据接口
    public boolean deleteAll() {
        SQLiteDatabase db = mDBOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            try {
                db.delete(TABLE_NAME, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
