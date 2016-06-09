package com.rosejames.picdemo.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rosejames.picdemo.R;
import com.rosejames.picdemo.config.Urls;
import com.rosejames.picdemo.engine.HttpRequest;
import com.rosejames.picdemo.model.PicModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/8.
 */
public class RecyclerViewActivity extends Activity {


    private static final String TAG = "RecyclerViewActivity";

    RecyclerView mRecyclerView;
    private ArrayList<PicModel> mPicModels;

    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mPicModels = new ArrayList<>();
        mRecyclerAdapter = new RecyclerAdapter();
        //网络请求
        new Thread() {
            @Override
            public void run() {
                super.run();
                HttpRequest.request(Urls.URL_PIC_JSON, mHttpCallback);
            }
        }.start();

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                mRecyclerAdapter.setPicModels(mPicModels);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        GridLayoutManager layoutManager = new GridLayoutManager(RecyclerViewActivity.this, 4);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setAdapter(mRecyclerAdapter);
                    }
                });
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
