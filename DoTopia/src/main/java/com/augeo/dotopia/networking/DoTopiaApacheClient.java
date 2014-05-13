package com.augeo.dotopia.networking;

import android.net.http.AndroidHttpClient;

import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by Krasimir on 5/13/2014.
 */

public class DoTopiaApacheClient extends retrofit.client.ApacheClient {

    public DoTopiaApacheClient(HttpClient client) {
        super(client);
    }

    @Override
    public Response execute(Request request) throws IOException {
        List<Header> headers = request.getHeaders();
        List<retrofit.client.Header> modified = new ArrayList<Header>();
        for (int i = 0; i < headers.size(); i++) {
            retrofit.client.Header header = headers.get(i);
            if (!header.getName().equals("Content-Length")) {
                modified.add(header);
            }
        }
        return super.execute(new Request(request.getMethod(), request.getUrl(), modified, request.getBody()));
    }
}
