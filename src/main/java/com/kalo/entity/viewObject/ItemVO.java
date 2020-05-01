package com.kalo.entity.viewObject;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Kalo
 * @create 2020-04-21 16:47
 */
@Data
public class ItemVO implements Serializable {
    private Integer id;
    private String title;
    private BigDecimal price;
    private Integer stock;
    private String description;
    private Integer sales;
    private String imgUrl;

    private Integer itemHasPromo;
    private Integer promoStatus;
    private BigDecimal promoPrice;
    private Integer promoId;
    private String promoStartDate;
    private String promoEndDate;
}
