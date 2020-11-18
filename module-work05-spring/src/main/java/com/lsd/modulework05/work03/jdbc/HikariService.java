package com.lsd.modulework05.work03.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-18 17:34
 * @Modified By：
 */
@Service
public class HikariService {

    @Autowired
    private HikariDataSource hikariDataSource;

    public void save(){


        Connection conn = null;
        try {


            conn = hikariDataSource.getConnection();
            conn.setAutoCommit(false);

            String sql = "insert into user (id, name) values (104, '我用的是Hikari')";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute(sql);

            conn.commit();

            System.out.println("Hikari! 保存成功");

        }catch (Exception e){

            e.printStackTrace();
            try {
                if(conn != null){
                    conn.rollback();
                }
            }catch (Exception ex){
            }
        }finally {

            if(conn != null){
                try {

                    conn.close();

                    hikariDataSource.evictConnection(conn);

                }catch (Exception e){
                }
            }
        }

    }


}
