package com.romariomkk.yelpproject.core.models;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Conditions {

//    @SerializedName("term")
//    String term;
//    @SerializedName("location")
//    String location;
//    @SerializedName("radius")
//    int radius;

    private Map<String, String> conditions;

    public Conditions()
    {
        conditions = new HashMap<>();
    }

    public Conditions add(@NonNull String key, @NonNull String value)
    {
        conditions.put(key, value);
        return this;
    }

    public Map<String, String> unstashConditions()
    {
        return conditions;
    }

//
//    public void setTerm(String term)
//    {
//        this.term = term;
//    }
//
//    public void setLocation(String location)
//    {
//        this.location = location;
//    }
//
//    public void setRadius(int radius)
//    {
//        this.radius = radius;
//    }
}
