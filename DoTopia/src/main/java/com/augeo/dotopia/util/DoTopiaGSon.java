package com.augeo.dotopia.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class DoTopiaGSon {
    private static Gson gson;
    public static Gson gson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)    //.LOWER_CASE_WITH_DASHES)
                            //.excludeFieldsWithoutExposeAnnotation()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                    .create();
        }
        return gson;
    }
}
