package com.example.testall.contactgallery;

public  class Contatcsmodel {
    private String id;
    private String name;
    private String email;
    private String gender;
    private String mobile;
    private String imageurl;

    public Contatcsmodel(String id, String imgurl, String email, String gender, String mobile,String name) {
        this.id = id;
        this.imageurl = imgurl;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
