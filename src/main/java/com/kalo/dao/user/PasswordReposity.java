package com.kalo.dao.user;

import com.kalo.entity.databaseObject.UserPassword;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kalo
 * @create 2020-04-20 16:08
 */
@Mapper
public interface PasswordReposity extends JpaRepository<UserPassword,Integer> {

    /**
     * 根据用户id查找密码
     * @param id
     * @return
     */
    UserPassword findByUserId(Integer id);
}
