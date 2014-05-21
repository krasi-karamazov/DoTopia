package com.augeo.dotopia.networking;
import com.augeo.dotopia.models.AuthenticationToken;
import com.augeo.dotopia.models.DeviceClientObject;
import com.augeo.dotopia.models.RegisterRequestBody;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public interface LoginRegisterService {

    @FormUrlEncoded
    @POST("/o/oauth/RegisterClient")
    public void registerDevice(@Field("token") String token, @Field("name") String name, @Field("devicetype") String deviceType, Callback<DeviceClientObject> callback);

    @FormUrlEncoded
    @POST("/o/oauth/token")
    public void getTokenAsUser(@Field("grant_type") String grantType, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("username") String userName, @Field("password") String password, Callback<AuthenticationToken> callback);

    @FormUrlEncoded
    @POST("/o/oauth/token")
    public void getTokenAsClient(@Field("grant_type") String grantType, @Field("client_id") String clientId, @Field("client_secret") String clientSecret, Callback<AuthenticationToken> callback);

    @POST("/r/user/registeruser")
    public void registerUser(@Header("Content-Type") String contentType, @Header("Authorization")String authorization, @Body RegisterRequestBody body, Callback<DeviceClientObject> callback);
}
