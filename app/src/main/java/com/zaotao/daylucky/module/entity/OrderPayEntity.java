package com.zaotao.daylucky.module.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Description OrderPayEntity
 * Created by wangisu@qq.com on 12/23/20.
 */
public class OrderPayEntity {
    /**
     * request params
     */
    private int pay_type;
    private int report_id;
    private String mobile;

    public OrderPayEntity(int pay_type, int report_id, String mobile) {
        this.pay_type = pay_type;
        this.report_id = report_id;
        this.mobile = mobile;
    }

    /**
     * result data
     */
    private String orderinfo;
    private WeChatPayBean wechatpay;

    public int getPay_type() {
        return pay_type;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }

    public WeChatPayBean getWechatpay() {
        return wechatpay;
    }

    public static class WeChatPayBean {
        private String appid;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        @SerializedName("package")
        private String packageValue;
        private String sign;


        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageValue() {
            return packageValue;
        }

        public void setPackageValue(String packageValue) {
            this.packageValue = packageValue;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

}
