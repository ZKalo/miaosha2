package com.kalo.dao.user;

import com.kalo.entity.databaseObject.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kalo
 * @create 2020-04-20 16:07
 */
@Mapper
public interface UserReposity extends JpaRepository<UserInfo, Integer> {

    /**
     * 根据手机号获取UserInfo
     * @param telphone
     * @return
     */
    UserInfo findByTelphone(String telphone);
}
