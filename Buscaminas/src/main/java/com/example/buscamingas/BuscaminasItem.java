package com.example.buscamingas;

public class BuscaminasItem {
    private int mImageResource;
    private String mText1;

    public BuscaminasItem(int mImageResource, String mText1) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
    }

    public void setmImageResource(int mImageResource) {
        this.mImageResource = mImageResource;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }
}