package com.romariomkk.yelpproject.mvp;

import com.romariomkk.yelpproject.core.models.content.BusinessXtra;
import com.romariomkk.yelpproject.core.models.content.SurroundingBusinesses;

/**
 * Created by romariomkk on 15.06.2017.
 */
public interface MvpView {

    void refreshPoints(SurroundingBusinesses businesses);
    void moveToSingleBusiness(BusinessXtra businessXtra);
}
