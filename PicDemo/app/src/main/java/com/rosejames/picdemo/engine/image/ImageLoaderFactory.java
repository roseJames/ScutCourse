package com.rosejames.picdemo.engine.image;

/**
 * Created by rosejames on 15/11/25.
 */
public class ImageLoaderFactory {

    private static ImageLoaderFactory mSingleton;
    private static GlideLoader mGPGlideLoader;

    public static synchronized IImageLoader getInstance() {
        if (mSingleton == null) {
            mSingleton = new ImageLoaderFactory();
        }
        return mSingleton.getImageLoader();
    }


    private synchronized IImageLoader getImageLoader() {
        if (mGPGlideLoader == null) {
            mGPGlideLoader = new GlideLoader();
        }
        return mGPGlideLoader;
    }
}
