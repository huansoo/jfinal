package com.jfsnpm.jfsnpm.core.util;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * Created by yangchuanhuan on 16/4/13.
 * controller返回数据工具类
 */
public class ApiResult<T> implements Serializable {

    private int status;

    private T result;

    public void setSuccessData(T object){
        status = 200;
        result = object;
    }
    public void setExceptionData(T object){
        status = 500;
        result = object;
    }

    public void setException(int code, T object){
        this.status = code;
        this.result = object;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
