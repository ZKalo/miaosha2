package com.kalo.dao.item;

import com.kalo.entity.databaseObject.ItemStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kalo
 * @create 2020-04-21 16:23
 */
@Mapper
public interface ItemStockRepostiy extends JpaRepository<ItemStock, Integer> {
    /**
     * 通过商品id来查找库存
     * @param id
     * @return
     */
    ItemStock findByItemId(Integer id);

    /**
     * 更新库存
     * @param amount
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "UPDATE item_stock SET stock = stock - ?1 WHERE item_id = ?2",nativeQuery = true)
    int updateStock(@Param("amount") Integer amount, @Param("itemId") Integer id);
}
