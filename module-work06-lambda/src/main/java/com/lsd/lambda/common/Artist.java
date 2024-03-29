package com.lsd.lambda.common;

/**
 * @author nhsoft.lsd
 */
public class Artist {

    private String name;

    private String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Artist() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
