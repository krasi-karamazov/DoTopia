package com.augeo.dotopia.networking;

import android.util.Log;

import com.augeo.dotopia.application.DoTopiaApplication;
import com.augeo.dotopia.models.AuthenticationToken;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DoTopiaGSon;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class RestServiceCreator {

    private static RestAdapter adapter;

    private static RestAdapter getRestAdapter() {

        if (adapter == null) {
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(15, TimeUnit.SECONDS);
            client.setReadTimeout(15, TimeUnit.SECONDS);

            RestAdapter.Builder adapterBuilder = new RestAdapter.Builder()
                    .setEndpoint(Constants.ENDPOINT)
                    .setConverter(new GsonConverter(DoTopiaGSon.gson()))
                    .setRequestInterceptor(new RequestInterceptor() {
                        @Override public void intercept(RequestFacade requestFacade) {
                            AuthenticationToken authorizationToken = DoTopiaApplication.getAuthenticationToken();
                            if (authorizationToken != null) {
                                String token = authorizationToken.getAccessToken();
                                //requestFacade.addHeader("Authorization", "Bearer " + token);
                            }
                        }
                    })
                    .setClient(new OkClient(client));
            if (Constants.DEBUG) {
                adapterBuilder.setLog(new RestAdapter.Log() {
                    @Override public void log(String s) {
                        Log.d("Retrofit", s);
                    }
                })
                        .setLogLevel(RestAdapter.LogLevel.FULL);
            } else {
                adapterBuilder.setLogLevel(RestAdapter.LogLevel.NONE);
            }
            adapter = adapterBuilder.build();
        }
        return adapter;
    }

    public static LoginRegisterService createLoginRegisterService(){
        return getRestAdapter().create(LoginRegisterService.class);
    }
}
