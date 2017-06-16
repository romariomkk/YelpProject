package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class SurroundingBusinesses {

    @SerializedName("total")
    private long total;
    @SerializedName("businesses")
    private List<Business> businesses = null;
    @SerializedName("region")
    private Region region;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


}
