package com.lsd.modulework05starter.jdbc;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 23:03
 * @Modified By：
 */
@ConfigurationProperties(prefix = "lsd.jdbc")
public class JdbcProperties {

    /**
     * 驱动
     */
    private String driver;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 初始化连接池大小
     */
    private Integer initPoolSize;

    /**
     * 连接池最大大小
     */
    private Integer maxPoolSize;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getInitPoolSize() {
        return initPoolSize;
    }

    public void setInitPoolSize(Integer initPoolSize) {
        this.initPoolSize = initPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
}
