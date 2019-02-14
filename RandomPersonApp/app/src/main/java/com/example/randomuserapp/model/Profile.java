package com.example.randomuserapp.model;

import java.util.ArrayList;

public class Profile {

    //Assert private variables
    private int id;
    private String author;
    private String imgUrl;
    private ArrayList<String> imgWallUrl;
    private String description;
    private String photographer;
    private String address;
    private String material;
    private double latitude;
    private double longitude;
    public Profile(int id, String author, String material, String address, String photographer, String description, double latitude, double longitude){
        this.id = id;
        this.author = author;
        this.material = material;
        this.address = address;
        this.photographer = photographer;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //Set the image url for this profile
    public Profile setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
        return this;
    }

    //Set arrayList for image urls
    public Profile setWallImgUrl(ArrayList<String> imgWallUrl){
        this.imgWallUrl = imgWallUrl;
        return this;
    }

    //get...() methods
    public int getId() {                                                                            //get method for ID
        return id;
    }
    public String getAuthor(){                                                                      //get method for author
        return this.author;
    }
    public String getImgUrl(){                                                                      //get method for image url
        return this.imgUrl;
    }
    public String getDescription(){                                                                 //get method for description
        return this.description;
    }
    public ArrayList<String> getImgWallUrl() {                                                      //get method for image url arrayList
        return imgWallUrl;
    }
    public String getPhotographer(){                                                                //get method for photographer
        return this.photographer;
    }
    public String getAddress() {                                                                    //get method for address
        return address;
    }
    public String getMaterial(){                                                                    //get method for material
        return this.material;
    }


    //Latitude and logitude for geolocation.
    public double getLatitude(){                                                                    //get method for latitude
        return this.latitude;
    }
    public double getLongitude(){                                                                   //get method for longitude
        return this.longitude;
    }

}
