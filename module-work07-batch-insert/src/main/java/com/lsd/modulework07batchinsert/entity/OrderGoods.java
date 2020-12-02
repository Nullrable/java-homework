package com.lsd.modulework07batchinsert.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * 订单商品(OrderGoods)实体类
 *
 * @author nhsoft.lsd
 * @since 2020-12-02 20:22:20
 */
@Entity
public class OrderGoods implements Serializable {
    private static final long serialVersionUID = -37500287401861699L;


    @EmbeddedId
    private OrderGoodsId id;
    /**
    * 商户ID
    */
    private Long merchantId;
    /**
    * 商品ID
    */
    private Long goodsId;
    /**
    * 名称
    */
    private String name;
    /**
    * 规格
    */
    private String spec;
    /**
    * 单位
    */
    private String unit;
    /**
    * 标准售价
    */
    private Double stdPrice;
    /**
    * 会员价
    */
    private Double memberPrice;
    /**
    * 详情URL
    */
    private String descUrl;
    /**
    * 媒体ID
    */
    private Long mediaId;
    /**
    * 媒体地址
    */
    private String mediaUrl;
    /**
    * 媒体类型
    */
    private String mediaType;
    /**
    * 营销活动ID
    */
    private Long marketActivityId;
    /**
    * 折扣方案ID
    */
    private Long discountSchemeId;
    /**
    * 实际售价
    */
    private Double salePrice;
    /**
    * 销售数量
    */
    private Double saleQty;
    /**
    * 销售金额
    */
    private Double saleMoney;
    /**
    * 折扣金额
    */
    private Double saleDicountMoney;
    /**
    * 创建时间戳
    */
    private Long createTime;

    public OrderGoodsId getId() {
        return id;
    }

    public void setId(OrderGoodsId id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getStdPrice() {
        return stdPrice;
    }

    public void setStdPrice(Double stdPrice) {
        this.stdPrice = stdPrice;
    }

    public Double getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(Double memberPrice) {
        this.memberPrice = memberPrice;
    }

    public String getDescUrl() {
        return descUrl;
    }

    public void setDescUrl(String descUrl) {
        this.descUrl = descUrl;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Long getMarketActivityId() {
        return marketActivityId;
    }

    public void setMarketActivityId(Long marketActivityId) {
        this.marketActivityId = marketActivityId;
    }

    public Long getDiscountSchemeId() {
        return discountSchemeId;
    }

    public void setDiscountSchemeId(Long discountSchemeId) {
        this.discountSchemeId = discountSchemeId;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(Double saleQty) {
        this.saleQty = saleQty;
    }

    public Double getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }

    public Double getSaleDicountMoney() {
        return saleDicountMoney;
    }

    public void setSaleDicountMoney(Double saleDicountMoney) {
        this.saleDicountMoney = saleDicountMoney;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}