package com.example.jelebuplantationsystem;

public class UsersData {
    private String userId;
    private String username;
    private String email;
    private String gender;
    private String mobile;
    private String imageUrl;

    public UsersData(String userId, String username, String email, String gender, String mobile, String imageURL) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.mobile = mobile;
        this.imageUrl = imageURL;
    }

    public UsersData() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImageURL() {
        return imageUrl;
    }

    public void setImageURL(String imageURL) {
        this.imageUrl = imageURL;
    }
}
