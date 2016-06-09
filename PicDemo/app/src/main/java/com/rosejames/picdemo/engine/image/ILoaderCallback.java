package com.rosejames.picdemo.engine.image;

import android.graphics.Bitmap;

/**
 * Created by rosejames on 15/11/24.
 */
public interface ILoaderCallback {
    void onFinish(Bitmap bitmap);
}
