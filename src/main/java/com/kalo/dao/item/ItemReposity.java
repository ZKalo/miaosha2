package com.kalo.dao.item;

import com.kalo.entity.databaseObject.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Kalo
 * @create 2020-04-21 16:17
 */
@Mapper
public interface ItemReposity extends JpaRepository<Item, Integer> {

    /**
     * 更新销量
     * @param amount
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "UPDATE item  set sales = sales + ?1 where id=?2",nativeQuery = true)
    int updateSales(@Param("amount") Integer amount, @Param("itemId") Integer id);
}
