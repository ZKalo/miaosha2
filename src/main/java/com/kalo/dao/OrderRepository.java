package com.kalo.dao;

import com.kalo.entity.databaseObject.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kalo
 * @create 2020-04-22 13:10
 */
@Mapper
public interface OrderRepository extends JpaRepository<OrderInfo, String> {
}
