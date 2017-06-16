package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Location implements Serializable {

    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("address2")
    private String address2;
    @SerializedName("address3")
    private String address3;
    @SerializedName("state")
    private String state;
    @SerializedName("address1")
    private String address1;
    @SerializedName("zip_code")
    private String zipCode;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getAddress2()
    {
        return address2;
    }

    public void setAddress2(String address2)
    {
        this.address2 = address2;
    }

    public String getAddress3()
    {
        return address3;
    }

    public void setAddress3(String address3)
    {
        this.address3 = address3;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getAddress1()
    {
        return address1;
    }

    public void setAddress1(String address1)
    {
        this.address1 = address1;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

}
