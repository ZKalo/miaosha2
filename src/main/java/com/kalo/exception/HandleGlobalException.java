package com.kalo.exception;

import com.kalo.entity.DataEntity;
import com.kalo.exception.errorenum.ErrorEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Kalo
 * @create 2020-04-20 19:01
 */
@RestControllerAdvice

public class HandleGlobalException {

    @ExceptionHandler(value = BussinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public DataEntity handleDefineException(BussinessException ex) {
        System.out.println("ex: "+ex.getErrorEnum().getErrorMsg());
        ex.printStackTrace();
        return new DataEntity(ex.getErrorEnum().getErrorCode(), ex.getErrorEnum().getErrorMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public DataEntity handleUnknowException(Exception e){
        e.printStackTrace();
        return new DataEntity(ErrorEnum.UNKNOW_ERROR.getErrorCode(), ErrorEnum.UNKNOW_ERROR.getErrorMsg());
    }
}
