package com.lsd.modulework07batchinsert;

import com.lsd.modulework07batchinsert.dao.TOrderDao;
import com.lsd.modulework07batchinsert.entity.TOrder;
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
    void contextLoads() {

        tOrderDao.batchSaveViaJpaNoBatch(create(1000000));
    }

    private List<TOrder> create(int size){

        Long startSize =  Long.valueOf(100000000000l);

        List<TOrder> orders = new ArrayList<>();

        for (int i = 0; i < size; i++){

            Long orderId = startSize + Long.valueOf(i);

            TOrder order = new TOrder();
            order.setOrderId(orderId);
            order.setUserName("nhsoft.lsd");
            order.setUserId(100000L);
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
