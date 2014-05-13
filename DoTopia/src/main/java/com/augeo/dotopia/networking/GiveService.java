package com.augeo.dotopia.networking;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;

/**
 * Created by Krasimir on 5/13/2014.
 */
public interface GiveService {

    @FormUrlEncoded
    @GET("/r/cause/search")
    public void getNonProfitOrganizations(@Field("searchTerm") String searchTerm, Callback<List<String>> callback);
}
