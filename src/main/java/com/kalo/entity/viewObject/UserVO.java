package com.kalo.entity.viewObject;

import lombok.Data;

/**
 * @author Kalo
 * @create 2020-04-20 15:49
 */
@Data
public class UserVO {
    private Integer id;
    private String name;
    private Byte gender;
    private  Integer age;
    private String telphone;
}
