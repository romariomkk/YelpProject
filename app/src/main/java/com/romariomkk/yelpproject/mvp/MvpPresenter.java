package com.romariomkk.yelpproject.mvp;

import com.romariomkk.yelpproject.core.main_func.MainApp;
import com.romariomkk.yelpproject.core.models.Conditions;
import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.SurroundingBusinesses;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class MvpPresenter {

    private MvpView mvpView;
    private RequestManager requestManager;

    public MvpPresenter(MvpView mvpView)
    {
        this.mvpView = mvpView;
        this.requestManager = MainApp.getInstance().getRequestManager();
    }

    public void requestAllBusinesses(Conditions conditions)
    {
        requestManager.requestAllBusinesses(conditions, new RequestManager.OnBusinessLoadedListener() {
            @Override
            public void onBusinessLoaded(SurroundingBusinesses businesses)
            {
                mvpView.refreshPoints(businesses);
            }

            @Override
            public void onBusinessLoaded(BusinessXtra business)
            {
                //left empty intentionally
            }
        });
    }

    public void requestSingleBusiness(String id)
    {
        requestManager.requestSingleBusiness(id, new RequestManager.OnBusinessLoadedListener() {
            @Override
            public void onBusinessLoaded(SurroundingBusinesses businesses)
            {
                //null left intentionally
            }

            @Override
            public void onBusinessLoaded(BusinessXtra business)
            {
                mvpView.moveToSingleBusiness(business);
            }
        });
    }

}
