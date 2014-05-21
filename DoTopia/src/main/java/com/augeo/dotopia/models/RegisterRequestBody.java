package com.augeo.dotopia.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by krasimir.karamazov on 5/21/2014.
 */
public class RegisterRequestBody {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("zip")
    private String zip;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
