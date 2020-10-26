package com.zaotao.daylucky.module.api;

/**
 * Description BaseResult
 * Created by wangisu@qq.com on 2019-08-14
 */
public class BaseResult<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isNull() {
        return data == null;
    }

    public boolean success() {
        return code == 200;
    }

    public boolean failure() {
        return code == 601;
    }
}
