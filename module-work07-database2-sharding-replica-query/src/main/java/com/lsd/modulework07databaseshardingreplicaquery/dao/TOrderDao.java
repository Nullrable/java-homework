package com.lsd.modulework07databaseshardingreplicaquery.dao;

import com.lsd.modulework07databaseshardingreplicaquery.entity.TOrder;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface TOrderDao {

    void batchSave(List<TOrder> list);

    List<TOrder> listByUser(Long userId);

    BigInteger count();

    TOrder read(Long orderId);

    void update(TOrder tOrder);

    TOrder save(TOrder tOrder);
}
