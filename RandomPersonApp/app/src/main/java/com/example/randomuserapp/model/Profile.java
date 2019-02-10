package com.example.randomuserapp.model;

import java.util.ArrayList;

public class Profile {

    private String author;      //Profiel/persoon
    private String imgUrl;      //url to image
    private ArrayList<String> imgWallUrl;
    private String description;
    private String fotograaf;
    private String adres;
    private String materiaal;
    public Profile(String author, String materiaal, String adres, String fotograaf, String description){
        this.author = author;
        this.materiaal = materiaal;
        this.adres = adres;
        this.fotograaf = fotograaf;
        this.description = description;
    }

    public Profile setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
        return this;
    }

    public Profile setWallImgUrl(ArrayList<String> imgWallUrl){
        this.imgWallUrl = imgWallUrl;
        return this;
    }

    public String getAuthor(){
        return this.author;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public String getDescription(){
        return this.description;
    }
    public String getFotograaf(){
        return this.fotograaf;
    }
    public String getMateriaal(){
        return this.materiaal;
    }

}
