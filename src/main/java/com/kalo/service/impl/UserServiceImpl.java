package com.kalo.service.impl;

import com.kalo.dao.user.PasswordReposity;
import com.kalo.dao.user.UserReposity;
import com.kalo.entity.UserModel;
import com.kalo.entity.databaseObject.UserInfo;
import com.kalo.entity.databaseObject.UserPassword;
import com.kalo.exception.BussinessException;
import com.kalo.exception.errorenum.ErrorEnum;
import com.kalo.service.UserService;
import com.kalo.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * @author Kalo
 * @create 2020-04-20 16:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReposity userReposity;

    @Autowired
    private PasswordReposity passwordReposity;

    @Override
    public void saveUser(UserModel userModel) throws NoSuchAlgorithmException,BussinessException {
        UserInfo userByPhone = userReposity.findByTelphone(userModel.getTelphone());
        if (userByPhone != null) {
            throw new BussinessException(ErrorEnum.USER_EXIST);
        }

        UserInfo userInfo1 = UserUtil.getUserInfo(userModel);
        System.out.println(userInfo1);
        UserInfo userInfo = userReposity.save(userInfo1);
        userModel.setId(userInfo.getId());

        System.out.println(userInfo);

        UserPassword userPassword = UserUtil.getUserPassword(userModel);
        passwordReposity.save(userPassword);
    }

    @Override
    public UserModel findUserByTelphone(String telphone) {
        UserInfo user = userReposity.findByTelphone(telphone);
        if (user == null) {
            return null;
        }
        UserPassword password = passwordReposity.findByUserId(user.getId());
        UserModel userModel = UserUtil.getUserModel(user, password);
        return userModel;
    }
}
