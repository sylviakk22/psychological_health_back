package com.example.houtai.domain;


import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private String email;

    public User() {
    }

    public User(String username, String password, String name, String gender, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

}

