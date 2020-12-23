package com.zaotao.daylucky.module.entity;

/**
 * Description WxPayResult
 * Created by wangisu@qq.com on 12/23/20.
 */
public class WxPayResult {

    private int errCode;
    public static final int NOT_INSTALLED_WE_CHAT = -900;

    public WxPayResult(int errCode) {
        this.errCode = errCode;
    }

    /**
     * 获取微信支付结果状态码
     *
     * @return 0成功  -1错误  -2取消  -900未安装微信
     */
    public int getErrCode() {
        return errCode;
    }

    public String getErrInfo() {
        String result = "";
        switch (errCode) {
            case 0:
                result = "支付成功";
                break;
            case -1:
                result = "支付错误";
                break;
            case -2:
                result = "支付取消";
                break;
            case -4:
                result = "拒绝支付";
                break;
            case NOT_INSTALLED_WE_CHAT:
                result = "您未安装最新版本微信，不支持微信支付，请安装或升级微信版本";
                break;
            default:
                break;
        }
        return result;
    }

    public boolean isSucceed() {
        // errCode 为0则代表支付成功
        return errCode == 0;
    }
}
