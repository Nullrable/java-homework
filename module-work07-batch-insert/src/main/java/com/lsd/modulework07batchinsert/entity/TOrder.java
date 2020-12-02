package com.lsd.modulework07batchinsert.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单(TOrder)实体类
 *
 * @author nhsoft.lsd
 * @since 2020-12-02 20:22:20
 */
@Entity
@Table(name = "t_order")
public class TOrder implements Serializable {
    private static final long serialVersionUID = 146053828952003684L;
    /**
    * 流水号
    */
    @Id
    private Long orderId;
    /**
    * 商户ID
    */
    private Long merchantId;
    /**
    * 用户ID
    */
    private Long userId;
    /**
    * 用户名称
    */
    private String userName;
    /**
    * 金额
    */
    private Double money;
    /**
    * 优惠金额
    */
    private Double dicountMoney;
    /**
    * 运费
    */
    private Double deliverFee;
    /**
    * 创建时间
    */
    private Long createTime;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getDicountMoney() {
        return dicountMoney;
    }

    public void setDicountMoney(Double dicountMoney) {
        this.dicountMoney = dicountMoney;
    }

    public Double getDeliverFee() {
        return deliverFee;
    }

    public void setDeliverFee(Double deliverFee) {
        this.deliverFee = deliverFee;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}