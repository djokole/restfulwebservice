package com.udemy.webservices.restfulwebservice.versioning;

public class PersonV1 {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonV1(String name) {
        this.name = name;
    }
}
