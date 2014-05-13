package com.augeo.dotopia.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class DeviceClientObject {
    public static final String CLIENT_ID_PREFS_KEY = "client_id";
    public static final String CLIENT_SECRET_PREFS_KEY = "client_secret";

    @SerializedName(CLIENT_ID_PREFS_KEY)
    private String clientId;
    @SerializedName(CLIENT_SECRET_PREFS_KEY)
    private String clientSecret;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
