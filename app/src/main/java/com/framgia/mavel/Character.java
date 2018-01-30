package com.framgia.mavel;

/**
 * Created by Dell on 29-Jan-18.
 */

public class Character {

    private String mName;
    private int mImage;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public Character(String name, int image) {
        mName = name;
        mImage = image;
    }
}
