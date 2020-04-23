package com.kalo.service;

import com.kalo.entity.UserModel;

import java.security.NoSuchAlgorithmException;

/**
 * @author Kalo
 * @create 2020-04-20 16:10
 */
public interface UserService {

    /**
     * 保存注册用户
     * @param userModel
     * @throws NoSuchAlgorithmException
     */
    void saveUser(UserModel userModel) throws NoSuchAlgorithmException;

    /**
     * 根据电话查找用户
     * @param telphone
     * @return
     */
    UserModel findUserByTelphone(String telphone);
}
