package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Category implements Serializable {

    @SerializedName("alias")
    private String alias;
    @SerializedName("title")
    private String title;

    public String getAlias()
    {
        return alias;
    }

    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

}
