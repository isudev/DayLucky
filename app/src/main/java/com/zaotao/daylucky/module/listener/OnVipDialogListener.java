package com.zaotao.daylucky.module.listener;

/**
 * Description OnVipDialogListener
 * Created by wangisu@qq.com on 12/23/20.
 */
public interface OnVipDialogListener {

    /**
     * pay click listener
     * @param mobile
     * @param payType 0 aliPay 1 WeChatPay
     */
    void onClick(String mobile,int payType);
}
