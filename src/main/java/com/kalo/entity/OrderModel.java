package com.kalo.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Kalo
 * @create 2020-04-22 13:14
 */
@Data
public class OrderModel {
    private String id;

    private Integer userId;

    @NotNull
    private Integer itemId;

    private BigDecimal itemPrice;

    @NotNull
    @Min(value = 1,message = "数量不能小于1")
    private Integer amount;

    private BigDecimal orderPrice;

    private Integer promoId;
}
