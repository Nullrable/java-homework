package com.lsd.modulework08database3.dao.impl;

import com.lsd.modulework08database3.dao.TOrderDao;
import com.lsd.modulework08database3.entity.TOrder;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-02 20:26
 * @Modified By：
 */
@Repository
@Transactional
public class TOrderDaoImpl implements TOrderDao {

    private final int BATCH_SIZE = 1000;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void batchSaveViaJpa(List<TOrder> list) {


        long start = System.currentTimeMillis();

        Iterator<TOrder> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()){
            entityManager.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                entityManager.flush();
                entityManager.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            entityManager.flush();
            entityManager.clear();
        }

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(String.format("耗时 %s 秒", timeElapsed/1000));

        //140秒

    }

    @Override
    public void batchSaveViaJpaNoBatch(List<TOrder> list) {

        long start = System.currentTimeMillis();

        for (TOrder order : list) {
            entityManager.persist(order);
        }

        entityManager.flush();
        entityManager.clear();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(String.format("耗时1 %s 秒", timeElapsed/1000));
    }

    @Override
    public List<TOrder> listByUser(Long userId) {

        String sql = "select * from t_order where user_id =:userId";

        Query sqlQuery = entityManager.createNativeQuery(sql, TOrder.class);
        sqlQuery.setParameter("userId", userId);


        return sqlQuery.getResultList();
    }
}
