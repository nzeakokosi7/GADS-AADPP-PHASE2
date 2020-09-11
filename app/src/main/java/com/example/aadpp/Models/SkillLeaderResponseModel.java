package com.example.aadpp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillLeaderResponseModel {
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("score")
    @Expose
    public String score;

    @SerializedName("country")
    @Expose
    public String country;

    @SerializedName("badgeUrl")
    @Expose
    public String badgeUrl;

    public SkillLeaderResponseModel() {
    }

    public SkillLeaderResponseModel(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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
        return "LearnerSkillResponse{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", country='" + country + '\'' +
                ", badgeUrl='" + badgeUrl + '\'' +
                '}';
    }
}
