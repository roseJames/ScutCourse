package com.rosejames.androidcompenent;

import android.os.Binder;

public class SelfBinder extends Binder {

    private String mMessage;

    public String getMessage() {
        return mMessage;
    }

    public SelfBinder setMessage(String message) {
        mMessage = message;
        return this;
    }
}