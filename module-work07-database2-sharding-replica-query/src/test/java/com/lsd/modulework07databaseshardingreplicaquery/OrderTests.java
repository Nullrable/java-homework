package com.lsd.modulework07databaseshardingreplicaquery;

import com.lsd.modulework07databaseshardingreplicaquery.dao.TOrderDao;
import com.lsd.modulework07databaseshardingreplicaquery.entity.TOrder;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderTests {

    @Autowired
    private TOrderDao tOrderDao;

    @Test
    void batchSave() {

        //主键手工生成
        tOrderDao.batchSave(create(10000));
    }



    @Test
    void save() {

        //主键按snowflake 生成
        TOrder order = new TOrder();
        order.setUserName("nhsoft.lsd");
        order.setUserId((long) (Math.random() * 100));
        order.setDicountMoney(1000D);
        order.setCreateTime(Calendar.getInstance().getTimeInMillis());
        order.setMerchantId(100000L);
        order.setMoney(1100D);
        order.setDeliverFee(101D);
        tOrderDao.save(order);
    }

    @Test
    void update() {

        TOrder tOrder = tOrderDao.read(100000000154L);
        tOrder.setUserName("hahaha1");

        tOrderDao.update(tOrder);
        System.out.println("更新成功");
    }


    @Test
    void list() {

        List<TOrder> orderList = tOrderDao.listByUser(9L);
        orderList.forEach( order -> System.out.println(order.toString()));
    }

    @Test
    void count() {

        BigInteger size = tOrderDao.count();
        System.out.println("记录数："  + size);
    }


    private List<TOrder> create(int size){

        Long startSize =  Long.valueOf(100000000000l);

        List<TOrder> orders = new ArrayList<>();

        for (int i = 0; i < size; i++){

            Long orderId = startSize + Long.valueOf(i);

            TOrder order = new TOrder();
            order.setOrderId(orderId);
            order.setUserName("nhsoft.lsd");
            order.setUserId((long) (Math.random() * 100));
            order.setDicountMoney(1000D);
            order.setCreateTime(Calendar.getInstance().getTimeInMillis());
            order.setMerchantId(100000L);
            order.setMoney(1100D);
            order.setDeliverFee(101D);
            orders.add(order);
        }

        return orders;



    }

}
