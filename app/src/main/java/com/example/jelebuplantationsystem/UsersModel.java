package com.example.jelebuplantationsystem;

public class UsersModel {

    private String email;
    private String name;
    private String phone;

    private UsersModel(){}
    private UsersModel(String email, String name, String phone){
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
