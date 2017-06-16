package com.romariomkk.yelpproject.core.main_func;

import android.app.Application;

import com.romariomkk.yelpproject.mvp.RequestManager;

import timber.log.Timber;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class MainApp extends Application {

    private static MainApp instance;

    public static MainApp getInstance()
    {
        return instance;
    }

    private RequestManager manager;

    public RequestManager getRequestManager()
    {
        return manager;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        manager = new RequestManager(this);
        Timber.plant(new Timber.DebugTree());
    }
}
