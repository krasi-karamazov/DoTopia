package com.augeo.dotopia.models;

import android.graphics.Bitmap;

/**
 * Created by krasimir.karamazov on 5/5/2014.
 */
public class SlidingMenuItemModel {
    public static final int NO_ICON = -1;
    private int mIcon;
    private String mLabel;
    private boolean mIsUser;

    public SlidingMenuItemModel() {

    }

    public SlidingMenuItemModel(int icon, String label, boolean isUser) {
        mIcon = icon;
        mLabel = label;
        mIsUser = isUser;
    }

    public void setIconId(int icon) {
        mIcon = icon;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setIsUser(boolean isUser) {
        mIsUser = isUser;
    }

    public boolean getIsUser() {
        return mIsUser;
    }

}
