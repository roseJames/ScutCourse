package com.rosejames.picdemo.model;

import com.rosejames.picdemo.config.FilePath;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by rosejames on 16/6/7.
 */
public class PicModel implements Serializable {

    private static final long serialVersionUID = 6637071735654830899L;

    private String mPicName;
    private String mPicUrl;


    public String getPicName() {
        return mPicName;
    }

    public PicModel setPicName(String picName) {
        mPicName = picName;
        return this;
    }

    public String getPicUrl() {
        return mPicUrl;
    }

    public PicModel setPicUrl(String picUrl) {
        mPicUrl = picUrl;
        return this;
    }

    public void saveSelf() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FilePath.SDCARD_SAVE_PIC_OBJECT));
            outputStream.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PicModel initSelf() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FilePath.SDCARD_SAVE_PIC_OBJECT));
            Object object = objectInputStream.readObject();
            if (object != null && object instanceof PicModel) {
                return (PicModel) object;
            } else {
                return new PicModel();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new PicModel();
        }
    }
}
