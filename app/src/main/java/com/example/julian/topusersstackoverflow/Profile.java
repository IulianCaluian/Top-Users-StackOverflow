package com.example.julian.topusersstackoverflow;

/**
 * Created by Julian on 09.03.2018.
 */

public class Profile {
    private int mRank;
    private String mName;
    private String mLocation;
    private String mImageUrl;
    private int nrMedalsGold;
    private int nrMedalsSilver;
    private int nrMedalsBronze;


    public Profile(){
        nrMedalsBronze = nrMedalsGold = nrMedalsSilver = 0;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public int getNrMedalsGold() {
        return nrMedalsGold;
    }

    public void setNrMedalsGold(int nrMedalsGold) {
        this.nrMedalsGold = nrMedalsGold;
    }

    public int getNrMedalsSilver() {
        return nrMedalsSilver;
    }

    public void setNrMedalsSilver(int nrMedalsSilver) {
        this.nrMedalsSilver = nrMedalsSilver;
    }

    public int getNrMedalsBronze() {
        return nrMedalsBronze;
    }

    public void setNrMedalsBronze(int nrMedalsBronze) {
        this.nrMedalsBronze = nrMedalsBronze;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int rank) {
        mRank = rank;
    }
}
