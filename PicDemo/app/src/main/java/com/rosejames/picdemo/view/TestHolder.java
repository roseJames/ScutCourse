package com.rosejames.picdemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.rosejames.picdemo.R;
import com.rosejames.picdemo.model.PicModel;

/**
 * Created by rosejames on 16/6/8.
 */
public class TestHolder extends RecyclerView.ViewHolder {

    private TextView mTextView;
    private PicImageView mPicImageView;

    public TestHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.item_tv);
        mPicImageView = (PicImageView) itemView.findViewById(R.id.item_iv);
    }

    public void setData(PicModel pic) {
        mTextView.setText(pic.getPicName());
        mPicImageView.setImage(pic.getPicUrl());
    }

}
