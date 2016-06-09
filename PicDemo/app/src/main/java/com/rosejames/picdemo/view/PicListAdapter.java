package com.rosejames.picdemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rosejames.picdemo.R;
import com.rosejames.picdemo.model.PicModel;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/8.
 */
public class PicListAdapter extends BaseAdapter {

    private ArrayList<PicModel> mPicModels;

    public PicListAdapter(Context context) {
        mPicModels = new ArrayList<>();
    }

    public void setPicModels(ArrayList<PicModel> picModels) {
        mPicModels = picModels;
    }

    @Override
    public int getCount() {
        return mPicModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mPicModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mPicModels.get(position).hashCode();
    }

    public static class ViewHolder {
        public TextView mTv;
        public PicImageView mIv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mIv.setImage(mPicModels.get(position).getPicUrl());
            viewHolder.mTv.setText(mPicModels.get(position).getPicName());
        } else {
            ViewHolder viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_list_item, null);
            viewHolder.mIv = (PicImageView) convertView.findViewById(R.id.item_iv);
            viewHolder.mTv = (TextView) convertView.findViewById(R.id.item_tv);
            viewHolder.mIv.setImage(mPicModels.get(position).getPicUrl());
            viewHolder.mTv.setText(mPicModels.get(position).getPicName());
            convertView.setTag(viewHolder);
        }
        return convertView;
    }
}
