package com.lsd.modulework05.work03.jdbc;

import com.lsd.modulework05starter.jdbc.LsdConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-18 16:40
 * @Modified By：
 */
@Component
public class JdbcService {

    @Autowired
    private LsdConnectionPool pool;


    public void delete(){
        Connection conn = null;
        try {


            conn = pool.fetch();
            conn.setAutoCommit(false);

            String sql = "delete from user where id = 102";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute(sql);

            conn.commit();

            System.out.println("删除成功");

        }catch (Exception e){

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

                    pool.release(conn);

                }catch (Exception e){
                }
            }
        }
    }

    public void update(){
        Connection conn = null;
        try {


            conn = pool.fetch();
            conn.setAutoCommit(false);

            String sql = "update user set name = '测试，我修改了' where id = 102";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute(sql);

            conn.commit();

            System.out.println("更新成功");

        }catch (Exception e){

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

                    pool.release(conn);

                }catch (Exception e){
                }
            }
        }
    }


    public List<User> find(){
        Connection conn = null;
        try {

            conn = pool.fetch();

            String sql = "select * from user where id = '102'";

            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<User> userList = new ArrayList<>();
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");

                User user = new User();
                user.setId(id);
                user.setName(name);
                userList.add(user);
            }
            System.out.println("查询成功");

            return userList;


        }catch (Exception e){

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

                    pool.release(conn);

                }catch (Exception e){
                }
            }
        }

        return null;
    }

    public void save(){

        Connection conn = null;
        try {


            conn = pool.fetch();
            conn.setAutoCommit(false);

            String sql = "insert into user (id, name) values (102, '测试1')";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.execute(sql);

            conn.commit();

            System.out.println("保存成功");

        }catch (Exception e){

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

                    pool.release(conn);

                }catch (Exception e){
                }
            }
        }

    }


    public void testTx(){

        Connection conn = null;
        try {


            conn = pool.fetch();
            conn.setAutoCommit(false);

            String insetrtSql = "insert into user (id, name) values ('103', '测试1')";

            PreparedStatement ps = conn.prepareStatement(insetrtSql);

            ps.execute(insetrtSql);

            System.out.println("我先插入一条数据, name是 测试1");

            String updateSql = "update user set name = '测试1，我修改了' where id = '103'";

            ps.execute(updateSql);

            System.out.println("我先插入一条数据, name改为  测试1，我修改了");

//            System.out.println("在事务提交前抛一个运行时异常，看看事务是否会回滚");
//            int i = 1/0;

            conn.commit();

            System.out.println("保存成功");

        }catch (Exception e){

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

                    pool.release(conn);

                }catch (Exception e){
                }
            }
        }

    }


   // 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。
}
