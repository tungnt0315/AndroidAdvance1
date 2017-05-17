package com.example.tung.lesson6_retrofit_okhttp.model;

/**
 * Created by tung on 5/16/17.
 */

public class Country {

    private String name;
    private String capital;
    private String region;
    private int population;

    public Country() {
    }

    public Country(String name, String capital, String region, int population) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
