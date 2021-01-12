package com.lsd.cache.mybatis.entity;

import java.io.Serializable;

/**
 * (City)实体类
 *
 * @author nhsoft.lsd
 *
 */
public class City implements Serializable {
    private static final long serialVersionUID = -13615792014848328L;
    
    private Integer id;
    
    private String country;
    
    private String province;
    
    private String city;
    
    private Integer postSn;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPostSn() {
        return postSn;
    }

    public void setPostSn(Integer postSn) {
        this.postSn = postSn;
    }

}