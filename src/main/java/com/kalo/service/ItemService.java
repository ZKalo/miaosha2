package com.kalo.service;

import com.kalo.entity.ItemModel;
import com.kalo.entity.databaseObject.ItemStock;
import com.kalo.entity.viewObject.ItemVO;

import java.util.List;

/**
 * @author Kalo
 * @create 2020-04-21 16:42
 */
public interface ItemService {
    /**
     * 查询所有商品
     * @return
     */
    List<ItemVO> findAllItem();

    /**
     * 保存商品
     * @param itemModel
     */
    void creatItem(ItemModel itemModel);

    /**
     * 根据id获取商品
     * @param id
     * @return
     */
    ItemVO findItemById(Integer id);

    /**
     * 更新销量
     * @param amount
     * @return
     */
    int updateSales(Integer amount, Integer id);

    /**
     * 更新库存
     * @param amount
     * @param ID
     * @return
     */
    int updateStock(Integer amount, Integer ID);

    /**
     * 根据商品id查找库存
     * @param id
     * @return
     */
    ItemStock findStockByItemId(Integer id);
}
