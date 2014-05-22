package com.augeo.dotopia.util;

/**
 * Created by krasimir.karamazov on 5/13/2014.
 */
public class Constants {
    public static final String DOTOPIA_CONTACT_EMAIL = "dotopia@dotopia.com";
    public static final String SHARED_PREFS_NAME = "dotopiaprefs";
    public static final String SHARED_PREFS_REGISTERED_MAIL_KEY = "registered_mail";

    public static boolean DEBUG = true;
    public static boolean NO_REGISTER = true;
    public static String CONTENT_TYPE = "application/json";

    public static final String ENDPOINT = (DEBUG)?"https://intapi.dotopia.com":"";
}

