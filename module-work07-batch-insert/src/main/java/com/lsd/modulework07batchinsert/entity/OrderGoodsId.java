package com.lsd.modulework07batchinsert.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-02 20:23
 * @Modified By：
 */
@Embeddable
public class OrderGoodsId implements Serializable {

    /**
     * 单据流水号
     */
    private Long orderId;
    /**
     * 单据明细编号
     */
    private Integer orderGoodsNum;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderGoodsNum() {
        return orderGoodsNum;
    }

    public void setOrderGoodsNum(Integer orderGoodsNum) {
        this.orderGoodsNum = orderGoodsNum;
    }
}
