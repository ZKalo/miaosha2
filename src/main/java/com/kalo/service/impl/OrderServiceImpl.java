package com.kalo.service.impl;

import com.kalo.dao.OrderRepository;
import com.kalo.dao.PromoRepository;
import com.kalo.dao.SequenceRepository;
import com.kalo.entity.OrderModel;
import com.kalo.entity.UserModel;
import com.kalo.entity.databaseObject.ItemStock;
import com.kalo.entity.databaseObject.OrderInfo;
import com.kalo.entity.databaseObject.Promo;
import com.kalo.entity.databaseObject.SequenceInfo;
import com.kalo.entity.viewObject.ItemVO;
import com.kalo.entity.viewObject.UserVO;
import com.kalo.exception.BussinessException;
import com.kalo.exception.errorenum.ErrorEnum;
import com.kalo.service.ItemService;
import com.kalo.service.OrderService;
import com.kalo.service.UserService;
import com.kalo.util.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.nio.Buffer;

/**
 * @author Kalo
 * @create 2020-04-22 13:37
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceRepository sequenceRepository;

    @Autowired
    private PromoRepository promoRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrder(OrderModel model, UserVO vo) {
        UserModel user = userService.findUserByTelphone(vo.getTelphone());
        if (user == null) {
            throw new BussinessException(ErrorEnum.USER_NOT_EXIST);
        }
        ItemVO item = itemService.findItemById(model.getItemId());
        if (item == null) {
            throw new BussinessException(ErrorEnum.PARAMETER_ERROR);
        }

        ItemStock stock = itemService.findStockByItemId(model.getItemId());
        if (stock.getStock() < model.getAmount()) {
            throw new BussinessException(ErrorEnum.ITEM_NOT_ENOUGHT);
        }

        SequenceInfo sequenceInfo = sequenceRepository.getOne("order_info");
        String orderId = OrderUtil.getOrderId(sequenceInfo);
        model.setId(orderId);
        model.setUserId(user.getId());


        BigDecimal price;
        if (model.getPromoId() != null) {
            Promo promo = promoRepository.getOne(model.getPromoId());
            if (promo == null) {
                throw new BussinessException(ErrorEnum.PARAMETER_ERROR);
            }
            model.setItemPrice(promo.getPromoItemPrice());
            price = promo.getPromoItemPrice().multiply(BigDecimal.valueOf(model.getAmount()));
        }else {
            model.setItemPrice(item.getPrice());
            price = item.getPrice().multiply(BigDecimal.valueOf(model.getAmount()));
        }
        model.setOrderPrice(price);

        OrderInfo orderInfo = OrderUtil.getOrderInfo(model);
        orderRepository.save(orderInfo);

        sequenceInfo.setCurrentValue(sequenceInfo.getCurrentValue() + sequenceInfo.getStep());
        sequenceRepository.save(sequenceInfo);

        itemService.updateSales(model.getAmount(), model.getItemId());
        itemService.updateStock(model.getAmount(),model.getItemId());


    }
}
