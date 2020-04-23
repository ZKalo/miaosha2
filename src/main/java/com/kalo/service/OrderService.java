package com.kalo.service;

import com.kalo.entity.OrderModel;
import com.kalo.entity.viewObject.UserVO;

/**
 * @author Kalo
 * @create 2020-04-22 13:36
 */
public interface OrderService {
    /**
     * 创建订单
     * @param model
     */
    void createOrder(OrderModel model, UserVO userVO);
}
