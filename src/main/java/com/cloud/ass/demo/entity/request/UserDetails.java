package com.cloud.ass.demo.entity.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties
public class UserDetails {

    @NotNull(message = "User name should be String")
    private String username;

    @NotNull(message = " Name should be String")
    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
