package com.example.randomuserapp.model;

import java.util.ArrayList;

public class Profile {

    //Assert private variables
    private int mId;
    private String mAuthor;
    private String mImgUrl;
    private ArrayList<String> mImgWallUrl;
    private String mDescription;
    private String photographer;
    private String mAddress;
    private String mMaterial;
    private double mLatitude;
    private double longitude;

    //Constructor for Profile class.
    public Profile(int id, String author, String material, String address, String photographer, String description, double latitude, double longitude){
        this.mId = id;
        this.mAuthor = author;
        this.mMaterial = material;
        this.mAddress = address;
        this.photographer = photographer;
        this.mDescription = description;
        this.mLatitude = latitude;
        this.longitude = longitude;
    }

    //Set the image url for this profile
    public Profile setmImgUrl(String mImgUrl){
        this.mImgUrl = mImgUrl;
        return this;
    }

    //Set arrayList for image urls
    public Profile setWallImgUrl(ArrayList<String> imgWallUrl){
        this.mImgWallUrl = imgWallUrl;
        return this;
    }

    //get...() methods
    public int getmId() {                                                                            //get method for ID
        return mId;
    }
    public String getmAuthor(){                                                                      //get method for mAuthor
        return this.mAuthor;
    }
    public String getmImgUrl(){                                                                      //get method for image url
        return this.mImgUrl;
    }
    public String getmDescription(){                                                                 //get method for mDescription
        return this.mDescription;
    }
    public ArrayList<String> getmImgWallUrl() {                                                      //get method for image url arrayList
        return mImgWallUrl;
    }
    public String getPhotographer(){                                                                //get method for photographer
        return this.photographer;
    }
    public String getmAddress() {                                                                    //get method for mAddress
        return mAddress;
    }
    public String getmMaterial(){                                                                    //get method for mMaterial
        return this.mMaterial;
    }

    //Latitude and logitude for geolocation.
    public double getmLatitude(){                                                                    //get method for mLatitude
        return this.mLatitude;
    }
    public double getLongitude(){                                                                   //get method for longitude
        return this.longitude;
    }

}
