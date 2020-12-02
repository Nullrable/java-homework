package com.lsd.modulework07batchinsert.job;

import com.lsd.modulework07batchinsert.dao.TOrderDao;
import com.lsd.modulework07batchinsert.entity.TOrder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-02 21:54
 * @Modified By：
 */
@Component
// 指定其执行顺序,值越小优先级越高
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MyApplicationRunner.class);

    @Resource
    private TOrderDao tOrderDao;

    /**
     * 工程启动结束后会立即执行的方法
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        tOrderDao.batchSaveViaJpa(create(1000000));
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
