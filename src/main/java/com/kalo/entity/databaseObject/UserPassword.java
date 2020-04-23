package com.kalo.entity.databaseObject;

import javax.persistence.*;

@Entity(name = "user_password")
public class UserPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "encrpt_password")
    private String encrptPassword;

    @Column(name = "user_id")
    private Integer userId;

    public UserPassword() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword == null ? null : encrptPassword.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}