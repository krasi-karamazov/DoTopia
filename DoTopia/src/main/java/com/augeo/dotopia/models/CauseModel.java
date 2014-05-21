package com.augeo.dotopia.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by krasimir.karamazov on 5/19/2014.
 */
public class CauseModel {
    @SerializedName("Status")
    private String status;
    @SerializedName("Error")
    private String error;
    @SerializedName("Count")
    private int count;

    @SerializedName("Items")
    private List<SingleCauseModel> items;

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public int getCount() {
        return count;
    }

    public List<SingleCauseModel> getItems() {
        return items;
    }
}
