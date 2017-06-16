package com.romariomkk.yelpproject.core.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class PrefUtils {

    public final static String USER_TOKEN = "user_token";

    private static String getPrefName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    public static boolean setUserToken(Context context, String token) {
        if (context == null) {
            return false;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(getPrefName(context), Context.MODE_PRIVATE);
        return sharedPref.edit().putString(USER_TOKEN, token).commit();
    }

    public static String getUserToken(Context context) {
        if (context == null) {
            return null;
        }
        SharedPreferences sharedPref = context.getSharedPreferences(getPrefName(context), Context.MODE_PRIVATE);
        return sharedPref.getString(USER_TOKEN, null);
    }

}
