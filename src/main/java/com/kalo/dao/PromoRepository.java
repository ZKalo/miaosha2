package com.kalo.dao;

import com.kalo.entity.databaseObject.Promo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Kalo
 * @create 2020-04-22 19:44
 */
@Mapper
public interface PromoRepository extends JpaRepository<Promo,Integer> {

    /**
     * 通过itemId来获取秒杀活动
     * @param itemId
     * @return
     */
    List<Promo> findByItemId(Integer itemId);
}
