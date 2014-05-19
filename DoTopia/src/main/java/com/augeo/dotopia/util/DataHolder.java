package com.augeo.dotopia.util;

import com.augeo.dotopia.models.AuthenticationToken;

/**
 * Created by Krasimir on 5/13/2014.
 */
public class DataHolder {
    private static DataHolder sInstance;
    private AuthenticationToken mToken;
    public synchronized static DataHolder getInstance() {
        if(sInstance == null) {
            sInstance = new DataHolder();
        }
        return sInstance;
    }

    public void setAuthToken(AuthenticationToken token) {
        mToken = token;
    }

    public AuthenticationToken getAuthToken() {
        return mToken;
    }
}
