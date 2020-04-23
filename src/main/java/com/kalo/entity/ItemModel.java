package com.kalo.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Kalo
 * @create 2020-04-21 16:34
 */
@Data
public class ItemModel {
    private Integer id;

    @NotBlank(message = "商品名称不能为空")
    private String title;

    @NotNull
    @Min(value = 0, message = "商品价格不能低于0")
    private BigDecimal price;

    @NotNull
    @Min(value = 0, message = "库存不能少于0")
    private Integer stock;

    @NotBlank(message = "商品描述不能为空")
    private String description;

    private Integer sales;

    @NotBlank(message = "图片路径不能为空")
    private String imgUrl;
}
