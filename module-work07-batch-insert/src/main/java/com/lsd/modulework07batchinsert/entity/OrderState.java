package com.lsd.modulework07batchinsert.entity;

import java.io.Serializable;

/**
 * 订单状态(OrderState)实体类
 *
 * @author nhsoft.lsd
 * @since 2020-12-02 20:22:20
 */
public class OrderState implements Serializable {
    private static final long serialVersionUID = -93868266332000504L;
    /**
    * 单据流水号
    */
    private Long orderId;
    /**
    * 状态序号
    */
    private Integer stateNo;
    /**
    * 商户ID
    */
    private Long merchantId;
    /**
    * 状态编号
    */
    private Integer stateCode;
    /**
    * 状态名称
    */
    private String stateName;
    /**
    * 操作时间戳
    */
    private Long operateTime;
    /**
    * 操作人
    */
    private String operator;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStateNo() {
        return stateNo;
    }

    public void setStateNo(Integer stateNo) {
        this.stateNo = stateNo;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Long operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

}