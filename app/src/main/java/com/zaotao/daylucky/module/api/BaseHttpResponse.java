package com.zaotao.daylucky.module.api;

/**
 * Description BaseHttpResponse
 * Created by wangisu@qq.com on 2019-11-25
 */
public class BaseHttpResponse {
    private int code;
    private String msg;
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
}
