package com.example.houtai.req;

import lombok.Data;

@Data
public class EditRequest {

    private String username;

    private String name;

    private String password;
    private String gender;

    private String phone;

    private String email;
}
