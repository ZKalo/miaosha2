package com.kalo.entity;

import lombok.Data;

/**
 * @author Kalo
 * @create 2020-04-20 17:00
 */
@Data
public class DataEntity {

    private String status;

    private int code;

    private Object data;

    public DataEntity() {
        this.status = "success";
        this.code = 200;
    }

    public DataEntity(Object data) {
        this.data = data;
        this.status = "success";
        this.code = 200;
    }

    public DataEntity(int code, String status) {
        this.code = code;
        this.status = status;
    }
}
