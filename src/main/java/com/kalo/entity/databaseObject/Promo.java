package com.kalo.entity.databaseObject;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "promo")
public class Promo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "promo_name")
    private String promoName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "item_id")
    private Integer itemId;

    @Column(name = "promo_item_price")
    private BigDecimal promoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName == null ? null : promoName.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }
}