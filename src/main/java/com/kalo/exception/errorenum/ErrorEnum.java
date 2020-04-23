package com.kalo.exception.errorenum;

/**
 * @author Kalo
 * @create 2020-04-20 19:05
 */
public enum ErrorEnum {
    PARAMETER_ERROR(10001, "参数不合法"),
    UNKNOW_ERROR(10002, "未知错误"),


    USER_NOT_EXIST(20001, "用户不存在"),
    USER_LOGIN_FAIL(20002, "手机或者密码有误"),
    USER_NOT_LOGIN(20003, "用户还没登录"),
    USER_EXIST(20004,"用户已存在"),
    USER_VERIFICATION_CODE_FAIL(20005, "验证码错误"),


    ITEM_NOT_ENOUGHT(30001, "商品库存不足");

    private int errorCode;
    private String errorMsg;

    ErrorEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
