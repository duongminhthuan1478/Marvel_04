package com.framgia.mavel;

/**
 * Created by Dell on 29-Jan-18.
 */

public class Character {

    private String mName;
    private String mImage;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public Character(String name, String image) {
        mName = name;
        mImage = image;
    }
}
