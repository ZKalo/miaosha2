package com.kalo.util;

import com.kalo.entity.OrderModel;
import com.kalo.entity.databaseObject.OrderInfo;
import com.kalo.entity.databaseObject.SequenceInfo;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Kalo
 * @create 2020-04-22 14:19
 */
public class OrderUtil {

    public static OrderInfo getOrderInfo(OrderModel model) {
        if (model == null) {
            return null;
        }
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(model, orderInfo);
        orderInfo.setPromoId(1);
        return orderInfo;
    }

//    public static void main(String[] args) {
//        LocalDate now = LocalDate.now();
//        String time = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
//        System.out.println(time);
//    }

    public static String getOrderId(SequenceInfo info) {
        Integer value = info.getCurrentValue();
        StringBuilder sb = new StringBuilder();
        LocalDate now = LocalDate.now();
        String time = now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
        sb.append(time);

        int sequence = info.getCurrentValue() + info.getStep();
        String sequenceStr = String.valueOf(sequence);
        for(int i = 0; i < 6 - sequenceStr.length(); i++) {
            sb.append(0);
        }
        sb.append(sequenceStr);

        sb.append("66");
        return sb.toString();
    }

}
