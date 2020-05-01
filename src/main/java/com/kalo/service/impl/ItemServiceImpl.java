package com.kalo.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalo.dao.PromoRepository;
import com.kalo.dao.item.ItemReposity;
import com.kalo.dao.item.ItemStockRepostiy;
import com.kalo.entity.ItemModel;
import com.kalo.entity.databaseObject.Item;
import com.kalo.entity.databaseObject.ItemStock;
import com.kalo.entity.databaseObject.Promo;
import com.kalo.entity.viewObject.ItemVO;
import com.kalo.service.CacheService;
import com.kalo.service.ItemService;
import com.kalo.util.ItemUtil;
import com.kalo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Kalo
 * @create 2020-04-21 16:43
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemReposity itemReposity;

    @Autowired
    private ItemStockRepostiy stockRepostiy;

    @Autowired
    private PromoRepository promoRepository;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private CacheService cacheService;

    @Override
    public List<ItemVO> findAllItem() {
        List<ItemVO> list = new ArrayList<ItemVO>();
//        List<Object> allItemVO = redisUtil.lGet("allItemVO", 0, -1);
//        if (allItemVO.size() > 0)  {
//            Collections.addAll(list,allItemVO.toArray(new ItemVO[allItemVO.size()]));
//            return list;
//        }

        // 从本地缓存中取
        String localCache = (String)cacheService.getCache("allItemVO");
        if (!StringUtils.isEmpty(localCache)) {
            list = JSON.parseArray(localCache, ItemVO.class);
            return list;
        }

        // 从redis中取
        String allItemVO = (String)redisUtil.hget("item", "allItemVO");
        if (!StringUtils.isEmpty(allItemVO)) {
            cacheService.setCache("allItemVO", allItemVO);
            list = JSON.parseArray(allItemVO, ItemVO.class);
            return list;
        }

        List<Item> all = itemReposity.findAll();
        for (Item e : all) {
            ItemStock stock = stockRepostiy.findByItemId(e.getId());
            List<Promo> promoList = promoRepository.findByItemId(e.getId());
            Promo promo = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Promo p : promoList) {

                String curDate = format.format(new Date());
                String endDate = format.format(p.getEndDate());
                if (endDate.compareTo(curDate) >= 0) {
                    promo = p;
                    break;
                }
            }
            list.add(ItemUtil.getItemVo(e, stock, promo));
        }

        //redisUtil.lSetAll("allItemVO", list);
        redisUtil.hset("item", "allItemVO",  JSON.toJSON(list).toString());
        cacheService.setCache("allItemVO", JSON.toJSON(list).toString());
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void creatItem(ItemModel itemModel) {
        if (itemModel != null) {
            Item item = itemReposity.save(ItemUtil.getItem(itemModel));
            itemModel.setId(item.getId());
            ItemStock stock = stockRepostiy.save(ItemUtil.getItemStock(itemModel));
        }
    }

    @Override
    public ItemVO findItemById(Integer id) {
        Item item = itemReposity.getOne(id);
        ItemStock stock = stockRepostiy.findByItemId(item.getId());
        List<Promo> promoList = promoRepository.findByItemId(id);
        Promo promo = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Promo p : promoList) {

            String curDate = format.format(new Date());
            String endDate = format.format(p.getEndDate());
            if (endDate.compareTo(curDate) >= 0) {
                promo = p;
                break;
            }
        }
        return ItemUtil.getItemVo(item,stock,promo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateSales(Integer amount, Integer id) {
        return itemReposity.updateSales(amount, id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateStock(Integer amount, Integer id) {
        return stockRepostiy.updateStock(amount, id);
    }

    @Override
    public ItemStock findStockByItemId(Integer id) {
        return stockRepostiy.findByItemId(id);
    }
}

