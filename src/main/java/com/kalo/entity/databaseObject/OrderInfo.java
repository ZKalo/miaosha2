package com.kalo.entity.databaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "order_info")
public class OrderInfo {
    @Id
    private String id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "item_price")
    private BigDecimal itemPrice;

    private Integer amount;

    @Column(name = "order_price")
    private BigDecimal orderPrice;

    @Column(name = "promo_id")
    private Integer promoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }
}