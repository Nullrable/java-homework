package com.lsd.modulework08database3shardingxa.dao.impl;

import com.lsd.modulework08database3shardingxa.dao.TOrderDao;
import com.lsd.modulework08database3shardingxa.entity.TOrder;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-02 20:26
 * @Modified By：
 */
@Repository
public class TOrderDaoImpl implements TOrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void batchSave(List<TOrder> list) {

        long start = System.currentTimeMillis();

        Iterator<TOrder> iterator = list.iterator();
        while (iterator.hasNext()){
            entityManager.persist(iterator.next());

        }
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(String.format("耗时 %s 秒", timeElapsed/1000));

    }



    @Override
    public List<TOrder> listByUser(Long userId) {

        String sql = "select * from t_order where user_id =:userId";

        Query sqlQuery = entityManager.createNativeQuery(sql, TOrder.class);
        sqlQuery.setParameter("userId", userId);


        return sqlQuery.getResultList();
    }

    @Override
    public BigInteger count() {
        String sql = "select count(*) from t_order";

        Query sqlQuery = entityManager.createNativeQuery(sql);


        return (BigInteger) sqlQuery.getSingleResult();
    }

    @Override
    public void update(TOrder tOrder) {
        entityManager.merge(tOrder);
    }

    @Override
    public TOrder read(Long orderId) {
        return entityManager.find(TOrder.class, orderId);
    }

    @Override
    public TOrder save(TOrder tOrder) {
        entityManager.persist(tOrder);

        return tOrder;
    }

    @Override
    @Transactional
    @ShardingTransactionType(TransactionType.XA)  // 支持TransactionType.LOCAL, TransactionType.XA, TransactionType.BASE
    public void testXaCommit(TOrder order1, TOrder order2) {

        entityManager.persist(order1);
        entityManager.persist(order2);

        System.out.println("order1: " + order1.toString());
        System.out.println("order2: " + order2.toString());

    }
}
