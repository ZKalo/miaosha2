package com.kalo.controller;

import com.kalo.entity.DataEntity;
import com.kalo.entity.ItemModel;
import com.kalo.entity.viewObject.ItemVO;
import com.kalo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Kalo
 * @create 2020-04-21 16:44
 */
@RestController
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@Validated
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService service;

    /**
     * 获取所有的商品
     * @return
     */
    @GetMapping("/list")
    public DataEntity getAllItem() {
        List<ItemVO> allItem = service.findAllItem();
        return new DataEntity(allItem);
    }

    /**
     * 创建商品
     * @param itemModel
     * @return
     */
    @PostMapping("/create")
    public DataEntity createItem(@RequestBody @Validated ItemModel itemModel) {
        service.creatItem(itemModel);
        return new DataEntity();
    }

    /**
     * 根据商品id获取商品信息
     * @param id
     * @return
     */
    @GetMapping("/getOne")
    public DataEntity getOneItemById(@NotNull @RequestParam("id") Integer id) {
        ItemVO item = service.findItemById(id);
        return new DataEntity(item);

    }
}
