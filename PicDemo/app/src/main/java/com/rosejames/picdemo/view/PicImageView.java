package com.rosejames.picdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.rosejames.picdemo.engine.image.ImageLoaderFactory;

/**
 * Created by rosejames on 16/6/8.
 */
public class PicImageView extends ImageView {

    public PicImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PicImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PicImageView(Context context) {
        super(context);
    }

    public void setImage(String url) {
        ImageLoaderFactory.getInstance().loadImage(this, url);
    }
}
