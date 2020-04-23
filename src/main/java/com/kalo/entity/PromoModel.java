package com.kalo.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Kalo
 * @create 2020-04-22 19:41
 */
@Data
public class PromoModel {
    private Integer id;

    /**
     * 秒杀活动状态，1------还没开始；2---------进行中；3---------已结束
     */
    private Integer status;

    private String promoName;

    private Date startDate;

    private Date endDate;

    private Integer itemId;

    private BigDecimal promoItemProce;
}
