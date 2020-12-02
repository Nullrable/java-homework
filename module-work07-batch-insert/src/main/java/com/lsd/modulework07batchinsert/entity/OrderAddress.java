package com.lsd.modulework07batchinsert.entity;

import java.io.Serializable;

/**
 * 订单地址(OrderAddress)实体类
 *
 * @author nhsoft.lsd
 * @since 2020-12-02 20:22:17
 */
public class OrderAddress implements Serializable {
    private static final long serialVersionUID = 541520797641966333L;
    /**
    * 单据流水号
    */
    private Long orderId;
    /**
    * 商户ID
    */
    private Long merchantId;
    /**
    * 国家
    */
    private String country;
    /**
    * 省份
    */
    private String province;
    /**
    * 城市
    */
    private String city;
    /**
    * 街道
    */
    private String street;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 收货人
    */
    private String receiver;
    /**
    * 收货人地址
    */
    private String receiverPhone;
    /**
    * 备注
    */
    private String memo;
    /**
    * 创建时间戳
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}