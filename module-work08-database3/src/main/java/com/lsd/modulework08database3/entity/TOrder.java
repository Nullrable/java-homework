package com.lsd.modulework08database3.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 订单(TOrder)实体类
 *
 * @author nhsoft.lsd
 * @since 2020-12-02 20:22:20
 */
@Entity
@Table(name = "t_order")
@Data
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


}