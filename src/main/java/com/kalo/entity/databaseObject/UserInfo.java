package com.kalo.entity.databaseObject;

import javax.persistence.*;

@Entity(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * 1-----男，2-----------女
     */
    private Byte gender;

    private Integer age;

    private String telphone;

    @Column(name = "register_mode")
    private String registerMode;

    @Column(name = "third_party_id")
    private String thirdPartyId;

    public UserInfo() {
        this.registerMode = "byPhone";
        this.thirdPartyId = "11111111";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode == null ? null : registerMode.trim();
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId == null ? null : thirdPartyId.trim();
    }
}