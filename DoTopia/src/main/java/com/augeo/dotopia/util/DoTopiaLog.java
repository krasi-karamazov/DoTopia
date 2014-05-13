package com.augeo.dotopia.util;

import android.util.Log;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class DoTopiaLog {

    public static String LOG_TAG = "DoTopia";

    public static void d(String msg){
        if(Constants.DEBUG){
            Log.d(LOG_TAG, msg);
        }
    }

    public static void e(String msg){
        if(Constants.DEBUG){
            Log.e(LOG_TAG, msg);
        }
    }

    public static void w(String msg){
        if(Constants.DEBUG){
            Log.w(LOG_TAG, msg);
        }
    }

    public static void i(String msg){
        if(Constants.DEBUG){
            Log.i(LOG_TAG, msg);
        }
    }
}