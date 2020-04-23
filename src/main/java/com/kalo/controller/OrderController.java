package com.kalo.controller;

import com.kalo.entity.DataEntity;
import com.kalo.entity.OrderModel;
import com.kalo.entity.UserModel;
import com.kalo.entity.viewObject.ItemVO;
import com.kalo.entity.viewObject.UserVO;
import com.kalo.exception.BussinessException;
import com.kalo.exception.errorenum.ErrorEnum;
import com.kalo.service.ItemService;
import com.kalo.service.OrderService;
import com.kalo.service.UserService;
import com.kalo.util.JwtUtil;
import com.kalo.util.UserUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * @author Kalo
 * @create 2020-04-22 13:17
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@Validated
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/createOrder")
    public DataEntity createOrder(@Validated @RequestBody OrderModel model, HttpServletRequest request) {
        Cookie cookie = JwtUtil.getCookie(request);
        if (cookie == null) {
            throw new BussinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        Claims claims = JwtUtil.checkJWT(cookie.getValue());
        if (claims == null) {
            throw new BussinessException(ErrorEnum.UNKNOW_ERROR);
        }
        UserVO vo = UserUtil.getUserVOByJWT(claims);

        service.createOrder(model, vo);

        return new DataEntity();

    }
}
