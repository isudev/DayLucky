package com.zaotao.daylucky.module.entity;

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

    public int getPay_type() {
        return pay_type;
    }

    public String getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(String orderinfo) {
        this.orderinfo = orderinfo;
    }

}
