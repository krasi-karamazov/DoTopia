package com.augeo.dotopia.networking;

import android.util.Log;

import com.augeo.dotopia.application.DoTopiaApplication;
import com.augeo.dotopia.models.AuthenticationToken;
import com.augeo.dotopia.util.Constants;
import com.augeo.dotopia.util.DoTopiaGSon;
import com.squareup.okhttp.OkHttpClient;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.security.KeyStore;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.ApacheClient;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class RestServiceCreator {

    private static RestAdapter adapter;

    private static RestAdapter getRestAdapter() {

        if (adapter == null) {


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
                    .setClient(new DoTopiaApacheClient(getNewHttpClient()));
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

    public static DataService createDataServiceService(){
        return getRestAdapter().create(DataService.class);
    }

    private static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new SSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
