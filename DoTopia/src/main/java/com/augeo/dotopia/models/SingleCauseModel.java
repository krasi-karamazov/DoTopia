package com.augeo.dotopia.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by krasimir.karamazov on 5/19/2014.
 */
public class SingleCauseModel implements Serializable {
    @SerializedName("Name")
    private String name;
    @SerializedName("Id")
    private long id;
    @SerializedName("Type")
    private String type;
    @SerializedName("EIN")
    private String ein;
    @SerializedName("ImageUrl")
    private String imageURL;
    @SerializedName("Description")
    private String description;
    @SerializedName("Address")
    private String address;
    @SerializedName("Url")
    private String url;
    @SerializedName("Categories")
    private String categories;
    @SerializedName("SummaryUrl")
    private String summaryURL;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getEin() {
        return ein;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getCategories() {
        return categories;
    }

    public String getSummaryURL() {
        return summaryURL;
    }
}
