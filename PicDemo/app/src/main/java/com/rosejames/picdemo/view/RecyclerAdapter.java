package com.rosejames.picdemo.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rosejames.picdemo.R;
import com.rosejames.picdemo.model.PicModel;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/8.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<TestHolder> {

    private static final String TAG = "RecyclerAdapter";

    public RecyclerAdapter() {
        mPicModels = new ArrayList<>();
    }

    @Override
    public TestHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new TestHolder(LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.pic_list_item, null));
    }

    @Override
    public void onBindViewHolder(TestHolder testHolder, int i) {
        Log.i(TAG, "onBindViewHolder:" + i);
        testHolder.setData(mPicModels.get(i));
    }

    @Override
    public int getItemCount() {
        return mPicModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private ArrayList<PicModel> mPicModels;


    public void setPicModels(ArrayList<PicModel> picModels) {
        mPicModels = picModels;
    }

}
