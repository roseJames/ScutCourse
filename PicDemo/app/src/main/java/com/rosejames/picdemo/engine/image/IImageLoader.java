package com.rosejames.picdemo.engine.image;

import android.view.View;

import java.io.File;

/**
 * Created by rosejames on 15/11/24.
 */
public interface IImageLoader {

    void loadImage(View view, String url);

    void loadImage(View view, String url, ILoaderCallback callback);

    void loadImage(View view, String url, int defaultResId);

    void loadImage(View view, String url, int defaultResId, ILoaderCallback callback);

    void loadImage(String url, final ILoaderCallback callback);

    File loadImageSync(String url);

}
