package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class BusinessXtra extends Business {

    public String[] getPhotos()
    {
        return photos;
    }

    @SerializedName("photos")
    String[] photos;

}
