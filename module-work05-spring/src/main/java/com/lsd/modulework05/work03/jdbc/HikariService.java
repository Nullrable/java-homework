package com.lsd.modulework05.work03.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
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


    public void batchSave(List<TOrder> list){

        long start = System.currentTimeMillis();

        Connection conn = null;
        try {


            conn = hikariDataSource.getConnection();
            conn.setAutoCommit(false);


            for (int i = 0; i < 10000000; i ++) {


                int startIndex = i * 10000;

                int end = ( i + 1 ) * 10000 - 1;

                if (end > list.size()) {
                    end = list.size() - 1;
                }

                if (startIndex >= list.size()) {
                    break;
                }


                List<TOrder> orders = list.subList(startIndex, end);

                if (orders == null || orders.size() == 0){
                    break;
                }

                String sql = "insert into t_order (order_id, merchant_id, user_id, user_name, money, dicount_money, deliver_fee, create_time)" +
                        " values " + values(orders);

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.execute(sql);



            }

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

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(String.format("耗时 %s 秒", timeElapsed/1000));

    }

    public String values( List<TOrder> orders ){



        List<String> list = new ArrayList<>();
        orders.forEach( order -> {

            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(order.getOrderId());
            sb.append(",");
            sb.append(order.getMerchantId());
            sb.append(",");
            sb.append(order.getUserId());
            sb.append(",'");
            sb.append(order.getUserName());
            sb.append("',");
            sb.append(order.getMoney());
            sb.append(",");
            sb.append(order.getDicountMoney());
            sb.append(",");
            sb.append(order.getDeliverFee());
            sb.append(",");
            sb.append(order.getCreateTime());
            sb.append(") ");
            list.add(sb.toString());


        });

        return String.join(",", list);

    }


}
