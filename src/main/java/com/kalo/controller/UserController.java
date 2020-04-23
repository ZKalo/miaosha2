package com.kalo.controller;

import com.kalo.entity.DataEntity;
import com.kalo.entity.UserModel;
import com.kalo.entity.viewObject.UserVO;
import com.kalo.exception.BussinessException;
import com.kalo.exception.errorenum.ErrorEnum;
import com.kalo.service.UserService;
import com.kalo.util.JwtUtil;
import com.kalo.util.UserUtil;
import io.jsonwebtoken.Claims;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kalo
 * @create 2020-04-20 16:11
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
@Validated
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * 获取验证
     * @param telphone
     * @return
     */
    @GetMapping("/getotp/{telphone}")
    public DataEntity getOpt(@PathVariable("telphone") String telphone) {
        int random = (int)(Math.random() * 99999) + 10000;
        String opt = String.valueOf(random);
        System.out.println("telphone===="+telphone+"--------------"+opt);
        UserUtil.putOpt(telphone, opt);
        return new DataEntity(opt);
    }

    /**
     * 用户注册
     * @param userModel
     * @return
     * @throws NoSuchAlgorithmException
     * @throws BussinessException
     */
    @PostMapping("/register")
    public DataEntity register(@Validated @RequestBody UserModel userModel) throws NoSuchAlgorithmException,BussinessException {
        System.out.println(userModel);
        if (!userModel.getOtpCode().equals(UserUtil.getOptByTelphone(userModel.getTelphone()))) {
            throw new BussinessException(ErrorEnum.USER_VERIFICATION_CODE_FAIL);
        }
        UserUtil.removeOtp(userModel.getTelphone());
        userService.saveUser(userModel);
        return new DataEntity();
    }

    /**
     * 登录
     * @param telphone
     * @param password
     * @param response
     * @return
     * @throws NoSuchAlgorithmException
     * @throws BussinessException
     */
    @PostMapping("/login")
    public DataEntity login(@NotBlank @RequestParam("telphone") String telphone, @NotBlank @RequestParam("password") String password,
                            HttpServletResponse response) throws NoSuchAlgorithmException, BussinessException {
        UserModel userModel = userService.findUserByTelphone(telphone);
        if (userModel == null) {
            throw new BussinessException(ErrorEnum.USER_NOT_EXIST);
        }
        if (!UserUtil.enCodeByMD5(password).equals(userModel.getEncrptPassword())) {
            throw new BussinessException(ErrorEnum.USER_LOGIN_FAIL);
        }
        String token = JwtUtil.getUserToken(userModel);
        if (token == null) {
            throw new BussinessException(ErrorEnum.UNKNOW_ERROR);
        }
        Cookie cookie = new Cookie(JwtUtil.JWT_KEY, token);
        cookie.setMaxAge(1000 * 60 * 60 * 24 * 7);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new DataEntity();
    }
    @GetMapping("/getUserInfo")
    public DataEntity getUserInfo(HttpServletRequest request) {
        Cookie cookie = JwtUtil.getCookie(request);
        if (cookie == null) {
            throw new BussinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        Claims claims = JwtUtil.checkJWT(cookie.getValue());
        if (claims == null) {
            throw new BussinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserVO userVO = UserUtil.getUserVOByJWT(claims);
        return new DataEntity(userVO);
    }

//    @GetMapping("/exception/{id}")
//    public DataEntity testException(@PathVariable("id") Integer id) {
//        System.out.println("进入testException====================");
//        if (id != 1) {
//            throw new BussinessException(ErrorEnum.PARAMETER_ERROR);
//        }
//        return new DataEntity();
//    }
}
