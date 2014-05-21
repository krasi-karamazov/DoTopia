package com.augeo.dotopia.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by krasimir.karamazov on 5/21/2014.
 */
public class HistoryModel implements Serializable {

    @SerializedName("ID")
    private int id;
    @SerializedName("IsUser")
    private boolean isUser;
    @SerializedName("Name")
    private String name;
    @SerializedName("Date")
    private String date;
    @SerializedName("Amount")
    private int amount;
    @SerializedName("IsPanding")
    private int isPending;


    public int getId() {
        return id;
    }

    public boolean isUser() {
        return isUser;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public int getIsPending() {
        return isPending;
    }
}
