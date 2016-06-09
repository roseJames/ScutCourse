package com.rosejames.picdemo.model;

public class Contact {
    private String mId;
    private String mName;
    private String mPhoneNumber;

    public String getId() {
        return mId;
    }

    public Contact setId(String id) {
        mId = id;
        return this;
    }

    public String getName() {
        return mName;
    }

    public Contact setName(String name) {
        mName = name;
        return this;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public Contact setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
        return this;
    }
}