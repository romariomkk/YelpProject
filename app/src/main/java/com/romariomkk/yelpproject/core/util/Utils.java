package com.romariomkk.yelpproject.core.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by romariomkk on 16.06.2017.
 */
public class Utils {

    public static final int REQUEST_CALL_PHONE = 3;
    public static String[] PERMISSIONS_CALL_PHONE = {
            Manifest.permission.CALL_PHONE
    };

    public static void callToNumber(Activity activity, String number) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + number));
            activity.startActivity(callIntent);
        } else {
            verifyCallPhonePermissions(activity);
        }
    }

    public static void verifyCallPhonePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_CALL_PHONE,
                    REQUEST_CALL_PHONE
            );
        }
    }

}
