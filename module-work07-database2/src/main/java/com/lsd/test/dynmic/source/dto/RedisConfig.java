package com.lsd.test.dynmic.source.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class RedisConfig implements Serializable{


    private static final long serialVersionUID = -19312500963104171L;
    private String tenantId;
    private Integer database;
    private String host;
    private Integer port;
    private String password;


}
