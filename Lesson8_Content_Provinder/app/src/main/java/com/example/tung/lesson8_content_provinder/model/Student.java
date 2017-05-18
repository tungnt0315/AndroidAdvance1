package com.example.tung.lesson8_content_provinder.model;

/**
 * Created by tung on 5/18/17.
 */

public class Student {

    private int id;
    private String name;
    private String phone;

    public Student() {
    }

    public Student(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
