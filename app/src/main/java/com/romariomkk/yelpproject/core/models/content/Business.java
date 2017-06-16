package com.romariomkk.yelpproject.core.models.content;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by romariomkk on 15.06.2017.
 */
public class Business implements Serializable {

    @SerializedName("rating")
    private double rating;
    @SerializedName("price")
    private String price;
    @SerializedName("phone")
    private String phone;
    @SerializedName("id")
    private String id;
    @SerializedName("is_closed")
    private boolean isClosed;
    @SerializedName("categories")
    private List<Category> categories = null;
    @SerializedName("review_count")
    private long reviewCount;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("coordinates")
    private Coordinates coordinates;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("location")
    private Location location;
    @SerializedName("distance")
    private float distance;
    @SerializedName("transactions")
    private List<String> transactions = null;

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }

}
