package com.rosejames.picdemo.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.rosejames.picdemo.R;
import com.rosejames.picdemo.engine.HttpRequest;
import com.rosejames.picdemo.model.PicModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ListView mLvList;
    private ArrayList<PicModel> mPicModels;
    private PicListAdapter mPicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_list_view).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        findViewById(R.id.btn_recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
//        mPicModels = new ArrayList<>();
//        mPicListAdapter = new PicListAdapter(this);
//        SPCenter.putInt(SPKey.KEY_TEST, 1234);
//        SPCenter.getSharedPreferences().edit().putInt("abcd", 123);
//        SPCenter.getSharedPreferences().edit().apply();
//        Log.i(TAG, SPCenter.getSharedPreferences().getInt("abcd", 0) + ",Haha");
        //写对象测试
//        new PicModel().setPicName("I am PicModel").setPicUrl("http://www.baidu.com").saveSelf();
//        PicModel picModel = PicModel.initSelf();
//        Log.i(TAG, picModel.getPicName() + ", " + picModel.getPicUrl());
        //数据库测试
//        new PictureDao().add("我是图片1", "http://www.baidu.com");
//        ArrayList<PicModel> picModels = new PictureDao().findAllMessage();
//        for (int i = 0; i < picModels.size(); i++) {
//            Log.i(TAG, picModels.get(i).getPicName() + ", " + picModels.get(i).getPicUrl());
//        }
        //网络请求
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                HttpRequest.request(Urls.URL_PIC_JSON, mHttpCallback);
//            }
//        }.start();
//        //获取联系人
//        ArrayList<Contact> contacts = new ContactInfoCenter().getContacts();
//        for (int i = 0; i < contacts.size(); i++) {
//            Log.i(TAG, contacts.get(i).getId() + ", " + contacts.get(i).getName() + ", " + contacts.get(i).getPhoneNumber());
//        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mPicListAdapter.setPicModels(mPicModels);
                mLvList.setAdapter(mPicListAdapter);
            }
        }
    };

    private HttpRequest.HttpCallback mHttpCallback = new HttpRequest.HttpCallback() {
        @Override
        public void callback(boolean isSuc, String str) {
            if (isSuc) {
                //解析Json数据
                try {
                    Log.i(TAG, str);
                    JSONArray jsonArray = new JSONArray(str);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        mPicModels.add(new PicModel().setPicName(jsonObject.getString("picName"))
                                .setPicUrl(jsonObject.getString("picUrl")));
                    }
                    mHandler.sendEmptyMessage(0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}



