package com.lsd.modulework05starter.jdbc;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 23:06
 * @Modified By：
 */
public class LsdConnectionPool {

    /**
     * 数据库连接容器
     */
    private ArrayBlockingQueue<Connection> pool;

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


    @Autowired
    public LsdConnectionPool(JdbcProperties jdbcProperties)throws Exception {

        this.driver  = jdbcProperties.getDriver();
        if(driver == null){
            throw new NullPointerException("driver not null");
        }

        this.username = jdbcProperties.getUsername();
        if(username == null){
            throw new NullPointerException("username not null");
        }

        this.password = jdbcProperties.getPassword();
        if(password == null){
            throw new NullPointerException("password not null");
        }

        this.url = jdbcProperties.getUrl();
        if(url == null){
            throw new NullPointerException("url not null");
        }

        this.initPoolSize = jdbcProperties.getInitPoolSize() == null ? 10 : jdbcProperties.getInitPoolSize();
        this.maxPoolSize = jdbcProperties.getMaxPoolSize() == null ? initPoolSize : jdbcProperties.getMaxPoolSize();

        alfterPropertiesSet();

    }

    private void alfterPropertiesSet()throws Exception {

        // 实例化 JDBC Driver 中指定的驱动类实例
        Driver driver = (Driver) (Class.forName(this.driver).newInstance());
        DriverManager.registerDriver(driver); // 注册 JDBC 驱动程序

        //初始化线程池
        pool = new ArrayBlockingQueue<>(maxPoolSize);
        initConnection(initPoolSize);

    }


    public Connection fetch()throws Exception{

        Connection conn =  pool.poll();

        if (conn == null) {
            // 如果目前连接池中没有可用的连接
            boolean success = createConnection();
            if(success){
                conn = pool.poll();
            }

            if (conn == null) {
                return null;
            }
        }
        return conn;


    }

    public void release(Connection connection)throws InterruptedException{

        // 确保连接池存在，如果连接没有创建（不存在），直接返回
        if (pool == null) {
            System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
            return;
        }
        pool.offer(connection, 5, TimeUnit.SECONDS);

    }

    private void initConnection(int numConnections)throws Exception {

        if (numConnections < 0) {
            throw new IllegalArgumentException("numConnections must gt 0");
        }

        for (int x = 0; x < numConnections; x++) {

            //如果当前连接池数量大于等于maxPoolSize，则直接退出
            if (this.maxPoolSize > 0
                    && this.pool.size() >= this.maxPoolSize) {
                break;
            }
            try {
                pool.offer(buildConnection(), 5, TimeUnit.SECONDS);
            } catch (SQLException e) {
                System.out.println(" 创建数据库连接失败！ " + e.getMessage());
                throw new SQLException();
            }
        }

    }



    private boolean createConnection()throws Exception {

        //如果当前连接池数量大于等于maxPoolSize，则直接退出
        if (this.maxPoolSize > 0
                && this.pool.size() >= this.maxPoolSize) {
            return false;
        }
        try {
            return pool.offer(buildConnection(), 5, TimeUnit.SECONDS);
        } catch (SQLException e) {
            throw new SQLException();
        }

    }


    /**
     *  如果第一次创建一下，校验一下设置的maxPoolSize 比 数据库允许的最大连接数大，则将
     *  数据库最大连接数赋值给校验一下设置的maxPoolSize
     * @return
     * @throws Exception
     */
    private Connection buildConnection() throws Exception{

        Connection conn = DriverManager.getConnection(url, username, password);

        if (pool.size() == 0) {
            DatabaseMetaData metaData = conn.getMetaData();
            int driverMaxConnections = metaData.getMaxConnections();
            if (driverMaxConnections > 0
                    && this.maxPoolSize > driverMaxConnections) {
                this.maxPoolSize = driverMaxConnections;
            }
        }
        return conn;
    }
}
