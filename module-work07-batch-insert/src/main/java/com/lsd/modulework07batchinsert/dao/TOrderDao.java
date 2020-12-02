package com.lsd.modulework07batchinsert.dao;

import com.lsd.modulework07batchinsert.entity.TOrder;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface TOrderDao {

    void batchSaveViaJpa(List<TOrder> list);

    void batchSaveViaJpaNoBatch(List<TOrder> list);

}
