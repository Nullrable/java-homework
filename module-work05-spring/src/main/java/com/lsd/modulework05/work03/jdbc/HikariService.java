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


            for (int i = 0; i < 1000000; i ++) {


                int startIndex = i * 1000;

                int end = ( i + 1 ) * 1000 - 1;

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
                ps.addBatch();

                //每1000次提交一次
                if(i%1000==0){//可以设置不同的大小；如50，100，500，1000等等
                    ps.executeBatch();
                    conn.commit();
                    ps.clearBatch();
                }




            }

            conn.commit();


//            String sql = "insert into t_order (order_id, merchant_id, user_id, user_name, money, dicount_money, deliver_fee, create_time) values (?, ?, ?, ?,?, ?, ?, ?)";
//
//
//            PreparedStatement ps = conn.prepareStatement(sql);
//
//
//             for (int i = 0; i < list.size(); i ++) {
//
//                 TOrder order = list.get(i);
//
//                 ps.setLong(1, order.getOrderId());
//                 ps.setLong(2, order.getMerchantId());
//                 ps.setLong(3, order.getUserId());
//                 ps.setString(4, order.getUserName());
//                 ps.setDouble(5, order.getMoney());
//                 ps.setDouble(6, order.getDicountMoney());
//                 ps.setDouble(7, order.getDeliverFee());
//                 ps.setLong(8, order.getCreateTime());
//
//                ps.addBatch();
//
//                //每1000次提交一次
//                if(i%1000==0){//可以设置不同的大小；如50，100，500，1000等等
//                    ps.executeBatch();
//                    conn.commit();
//                    ps.clearBatch();
//                }
//
//            }

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
