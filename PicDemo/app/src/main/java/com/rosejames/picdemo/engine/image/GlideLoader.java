package com.rosejames.picdemo.engine.image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.rosejames.picdemo.config.PicDemoApplication;

import java.io.File;

/**
 * Created by rosejames on 15/11/25.
 */
public class GlideLoader implements IImageLoader {

    private static final String TAG = "GlideLoader";

    public GlideLoader() {

    }

    @Override
    public void loadImage(final View view, String url) {
        loadImage(view, url, 0);
    }

    @Override
    public void loadImage(final View view, String url, final ILoaderCallback callback) {
        loadImage(view, url, 0, callback);
    }

    @Override
    public void loadImage(final View view, String url, final int defaultResId) {
        loadImage(view, url, defaultResId, null);
    }

    @Override
    public void loadImage(String url, final ILoaderCallback callback) {
        RequestManager requestManager = Glide.with(PicDemoApplication.getApplication());
        requestManager.load(url).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String s, Target<Bitmap> target, boolean b) {
                callback.onFinish(null);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap bitmap, String s, Target<Bitmap> target, boolean b, boolean b1) {
                callback.onFinish(bitmap);
                return true;
            }
        }).into(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public File loadImageSync(String url) {
        FutureTarget<File> future =
                Glide.with(PicDemoApplication.getApplication()).load(url).downloadOnly(Integer.MAX_VALUE, Integer.MAX_VALUE);
        try {
            File cacheFile = future.get();
            return cacheFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void loadImage(View view, String url, int defaultResId, final ILoaderCallback callback) {
        RequestManager requestManager = getRequestManager(view);
        if (view instanceof ImageView) {
            final ImageView imageView = (ImageView) view;
            BitmapRequestBuilder builder = requestManager.load(url).asBitmap().animate(android.R.anim.fade_in).centerCrop();
            if (defaultResId > 0) {
                builder.error(defaultResId).placeholder(defaultResId);
            }
            if (callback != null) {
                builder.listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<Bitmap> target, boolean b) {
                        callback.onFinish(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap bitmap, String s, Target<Bitmap> target, boolean b, boolean b1) {
                        callback.onFinish(bitmap);
                        Log.i(TAG, "onFinish");
                        return false;
                    }
                });
                builder.diskCacheStrategy(DiskCacheStrategy.ALL).into(new ViewTarget<View, Bitmap>(imageView) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation anim) {
                    }
                });
            } else {
                builder.diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            }
        } else {
            BitmapRequestBuilder builder = requestManager.load(url).asBitmap().animate(android.R.anim.fade_in);
            if (defaultResId > 0) {
                builder.error(defaultResId).placeholder(defaultResId);
            }
            if (callback != null) {
                builder.listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<Bitmap> target, boolean b) {
                        callback.onFinish(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap bitmap, String s, Target<Bitmap> target, boolean b, boolean b1) {
                        Log.i(TAG, "onFinish");
                        callback.onFinish(bitmap);
                        return false;
                    }
                });
            }
            builder.diskCacheStrategy(DiskCacheStrategy.ALL).into(new ViewTarget<View, Bitmap>(view) {
                @Override
                public void onResourceReady(Bitmap resource, GlideAnimation anim) {
                    if (callback == null)
                        view.setBackgroundDrawable(new BitmapDrawable(resource));
                }
            });
        }
    }

    private RequestManager getRequestManager(View view) {
        return Glide.with(PicDemoApplication.getApplication());
    }
}
