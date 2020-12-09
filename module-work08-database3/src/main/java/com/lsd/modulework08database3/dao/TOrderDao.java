package com.lsd.modulework08database3.dao;

import com.lsd.modulework08database3.entity.TOrder;
import java.util.List;
/**
 * @Author: nhsoft.lsd
 */
public interface TOrderDao {

    void batchSaveViaJpa(List<TOrder> list);

    void batchSaveViaJpaNoBatch(List<TOrder> list);

    List<TOrder> listByUser(Long userId);

}
