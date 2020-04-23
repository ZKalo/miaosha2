package com.kalo.util;

import com.kalo.entity.ItemModel;
import com.kalo.entity.databaseObject.Item;
import com.kalo.entity.databaseObject.ItemStock;
import com.kalo.entity.databaseObject.Promo;
import com.kalo.entity.viewObject.ItemVO;
import javafx.scene.input.DataFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author Kalo
 * @create 2020-04-21 16:53
 */
public class ItemUtil {

    /**
     * 获取返回前端显示的ItemVo
     * @param item
     * @param stock
     * @return
     */
    public static ItemVO getItemVo(Item item, ItemStock stock, Promo promo) {
        if (item == null || stock == null) {
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(item,itemVO);
        itemVO.setStock(stock.getStock());
        itemVO.setItemHasPromo(0);
        if (promo != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDate = format.format(promo.getStartDate());
            String endDate = format.format(promo.getEndDate());
            String curDate = format.format(new Date());

            if (curDate.compareTo(endDate) > 0 || startDate.compareTo(endDate) >= 0) {
                return itemVO;
            }

            itemVO.setItemHasPromo(1);
            itemVO.setPromoId(promo.getId());
            itemVO.setPromoPrice(promo.getPromoItemPrice());

            int s = 0;
            s = curDate.compareTo(startDate) >= 0 ? 2 : 1;

            itemVO.setPromoStatus(s);
            itemVO.setPromoStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(promo.getStartDate()));
            itemVO.setPromoEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(promo.getEndDate()));
        }
        return itemVO;
    }

    public static void main(String[] args) {
        System.out.println("a".compareTo("f"));
        System.out.println("2018".compareTo("2014"));
    }

    /**
     * 获取跟数据库表Item对应的对象
     * @param itemModel
     * @return
     */
    public static Item getItem(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        Item item = new Item();
        BeanUtils.copyProperties(itemModel,item);
        item.setSales(0);
        return item;
    }

    /**
     * 获取跟数据库表Item_stock对应的对象
     * @param itemModel
     * @return
     */
    public static ItemStock getItemStock(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }
        ItemStock stock = new ItemStock();
       stock.setItemId(itemModel.getId());
       stock.setStock(itemModel.getStock());
       return stock;
    }
}
