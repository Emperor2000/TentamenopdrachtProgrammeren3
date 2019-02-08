package com.example.randomuserapp.model;

public class Person {

    private String title;
    private String first;
    private String last;
    private String imgUrl;

    public Person (String title, String first, String last){
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public Person setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
        return this;
    }

    public String getTitle(){
        return this.title;
    }
    public String getFirst(){
        return this.first;
    }
    public String getLast(){
        return this.last;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }

    public String getFullName(){
        return this.title + " " + this.first + " " + this.last;
    }

}
