package com.lsd.test.dynmic.source.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class TenantDbConfig implements Serializable {

    private String tenantId;
    private String name;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String dbDriver;
    private String publicKey;
    private Boolean readOnly; //是否只读

}
