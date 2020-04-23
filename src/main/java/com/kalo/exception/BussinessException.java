package com.kalo.exception;

import com.kalo.exception.errorenum.ErrorEnum;
import lombok.Data;

/**
 * @author Kalo
 * @create 2020-04-20 19:14
 */
@Data
public class BussinessException extends RuntimeException{
    private ErrorEnum errorEnum;

    public BussinessException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public BussinessException(ErrorEnum errorEnum, String msg) {
        this.errorEnum = errorEnum;
        this.errorEnum.setErrorMsg(msg);
    }

}
