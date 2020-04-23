package com.kalo.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.kalo.entity.UserModel;
import com.kalo.entity.databaseObject.UserInfo;
import com.kalo.entity.databaseObject.UserPassword;
import com.kalo.entity.viewObject.UserVO;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kalo
 * @create 2020-04-20 17:11
 */
public class UserUtil {
    private static Map<String, String> optMap = new HashMap<>(256);

    /**
     * 利用手机号获取验证码
     * @param telphone
     * @return
     */
    public static String getOptByTelphone(String telphone) {
        return optMap.get(telphone);
    }

    /**
     * 存验证码
     * @param telphone
     * @param opt
     * @return
     */
    public static String putOpt(String telphone, String opt) {
        return optMap.put(telphone, opt);
    }

    public static void removeOtp(String tel) {
        optMap.remove(tel);
    }

    /**
     * 获取加密字符串
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String enCodeByMD5(String str) throws NoSuchAlgorithmException {
        MessageDigest digest =MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newStr = base64Encoder.encode(digest.digest(str.getBytes()));
        return newStr;
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(enCodeByMD5("kalo"));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 根据UserModel获取UserInfo
     * @param userModel
     * @return
     */
    public static UserInfo getUserInfo(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel,userInfo);
        return userInfo;
    }

    /**
     * 根据UserModel获取UserPassword
     * @param userModel
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static UserPassword getUserPassword(UserModel userModel) throws NoSuchAlgorithmException {
        if (userModel == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(enCodeByMD5(userModel.getEncrptPassword()));
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    /**
     * 根据用户UserInfo和密码获取UserModel
     * @param userInfo
     * @param password
     * @return
     */
    public static UserModel getUserModel(UserInfo userInfo, UserPassword password) {
        UserModel userModel = new UserModel();
        if (userInfo == null || password == null) {
            return null;
        }
        BeanUtils.copyProperties(userInfo, userModel);
        userModel.setEncrptPassword(password.getEncrptPassword());
        return userModel;
    }

    /**
     * 根据JWT的解密信息获取返回给前端的UserVO
     * @param claims
     * @return
     */
    public static UserVO getUserVOByJWT(Claims claims) {
        if (claims == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setName((String) claims.get("name"));
        userVO.setGender(((Integer) claims.get("gender")).byteValue());
        userVO.setAge((Integer) claims.get("age"));
        userVO.setTelphone((String) claims.get("telphone"));
        return userVO;

    }
}
