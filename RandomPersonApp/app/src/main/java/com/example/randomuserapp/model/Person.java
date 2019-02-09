package com.example.randomuserapp.model;

public class Person {

    private String author;
    private String imgUrl;

    public Person (String author){
        this.author = author;
    }

    public Person setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
        return this;
    }

    public String getTitle(){
        return this.author;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public String getFullName(){
        return this.author;
    }

}
