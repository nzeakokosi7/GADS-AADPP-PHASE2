package com.example.aadpp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LearningLeaderResponseModel {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("hours")
    @Expose
    public String hours;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("badgeUrl")
    @Expose
    public String badgeUrl;


    public LearningLeaderResponseModel() {
    }

    public LearningLeaderResponseModel(String name, String hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "LearnerHourResponse{" +
                "name='" + name + '\'' +
                ", hours='" + hours + '\'' +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }
}
