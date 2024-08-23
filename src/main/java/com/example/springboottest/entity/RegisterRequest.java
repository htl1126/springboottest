package com.example.springboottest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    public String email, password;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("last_name")
    public String lastName;
}
