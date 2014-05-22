package com.augeo.dotopia.networking;

import com.augeo.dotopia.models.CauseModel;
import com.augeo.dotopia.models.HistoryModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Query;

/**
 * Created by krasimir.karamazov on 5/19/2014.
 */
public interface DataService {

    @GET("/r/cause/search")
    public void getCauses(@Header("Content-Type") String contentType, @Header("Authorization")String authorization,  @Query("searchTerm") String searchTerm, Callback<CauseModel> callback);

    @GET("/r/user/fullhistory")
    public void getHistory(@Header("Content-Type") String contentType, @Header("Authorization")String authorization, Callback<List<HistoryModel>> callback);
}
