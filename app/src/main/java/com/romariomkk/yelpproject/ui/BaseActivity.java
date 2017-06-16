package com.romariomkk.yelpproject.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class BaseActivity extends AppCompatActivity {

    protected void showDialog(String message)
    {
        if (!isFinishing())
        {
            try
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(message)
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        })
                        .create().show();
            } catch (Exception e)
            {
                Timber.e("Error while showing Alert Dialog", e);
            }
        }
    }

}
