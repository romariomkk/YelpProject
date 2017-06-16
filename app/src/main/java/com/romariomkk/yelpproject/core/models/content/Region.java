package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Region implements Serializable {

    @SerializedName("center")
    private Center center;

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

}