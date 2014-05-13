package com.augeo.dotopia.networking;
import com.augeo.dotopia.models.DeviceClientObject;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public interface LoginRegisterService {

    @FormUrlEncoded
    @POST("/o/oauth/RegisterClient")
    public void registerDevice(@Field("token") String token, @Field("name") String name, @Field("devicetype") String deviceType, Callback<DeviceClientObject> callback);
}
