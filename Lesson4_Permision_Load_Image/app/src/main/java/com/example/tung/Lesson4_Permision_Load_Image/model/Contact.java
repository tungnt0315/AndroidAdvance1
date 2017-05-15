package com.example.tung.Lesson4_Permision_Load_Image.model;

/**
 * Created by tung on 5/14/17.
 */

public class Contact {
    private String name;
    private String phone;

    public Contact(){}

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
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
