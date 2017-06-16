package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Center implements Serializable {

    @SerializedName("latitude")
    private float latitude;
    @SerializedName("longitude")
    private float longitude;

    public float getLatitude()
    {
        return latitude;
    }

    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    public float getLongitude()
    {
        return longitude;
    }

    public void setLongitude(float longitude)
    {
        this.longitude = longitude;
    }

}

