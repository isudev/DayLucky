package com.zaotao.daylucky.widget.dialog;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.Constants;
import com.zaotao.daylucky.app.AppDataManager;
import com.zaotao.daylucky.module.listener.OnVipDialogListener;
import com.zaotao.daylucky.view.activity.SelectActivity;
import com.zaotao.daylucky.widget.appview.AppFakeBoldTextView;

import razerdp.basepopup.BasePopupWindow;

/**
 * Description DialogUnlockedVip
 * Created by wangisu@qq.com on 12/23/20.
 */
public class DialogUnlockedVip extends BasePopupWindow {
    private AppCompatEditText viewUnlockedVipInput;
    private TextView viewUnlockedVipSelect;
    private AppFakeBoldTextView viewUnlockedVipSelectAliPay;
    private AppCompatImageView viewUnlockedVipSelectAliPayCheckBox;
    private AppFakeBoldTextView viewUnlockedVipSelectWeChatPay;
    private AppCompatImageView viewUnlockedVipSelectWeChatPayCheckBox;
    private AppCompatButton viewUnlockedVipButton;

    private String mobile;
    private int checkPosition;
    private OnVipDialogListener onVipDialogListener;

    public DialogUnlockedVip(Context context) {
        super(context);

        viewUnlockedVipInput = findViewById(R.id.view_unlocked_vip_input);
        viewUnlockedVipSelect = findViewById(R.id.view_unlocked_vip_select);
        viewUnlockedVipSelectAliPay = findViewById(R.id.view_unlocked_vip_select_ali_pay);
        viewUnlockedVipSelectAliPayCheckBox = findViewById(R.id.view_unlocked_vip_select_ali_pay_check_box);
        viewUnlockedVipSelectWeChatPay = findViewById(R.id.view_unlocked_vip_select_we_chat_pay);
        viewUnlockedVipSelectWeChatPayCheckBox = findViewById(R.id.view_unlocked_vip_select_we_chat_pay_check_box);
        viewUnlockedVipButton = findViewById(R.id.view_unlocked_vip_button);

        viewUnlockedVipSelectAliPay.setOnClickListener(v -> {
            viewUnlockedVipSelectAliPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_selected);
            viewUnlockedVipSelectWeChatPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_select_un);
            checkPosition = 0;
        });
        viewUnlockedVipSelectAliPayCheckBox.setOnClickListener(v -> {
            viewUnlockedVipSelectAliPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_selected);
            viewUnlockedVipSelectWeChatPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_select_un);
            checkPosition = 0;
        });

        viewUnlockedVipSelectWeChatPay.setOnClickListener(v -> {
            viewUnlockedVipSelectAliPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_select_un);
            viewUnlockedVipSelectWeChatPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_selected);
            checkPosition = 1;
        });

        viewUnlockedVipSelectWeChatPayCheckBox.setOnClickListener(v -> {
            viewUnlockedVipSelectAliPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_select_un);
            viewUnlockedVipSelectWeChatPayCheckBox.setImageResource(R.drawable.ic_unlocked_vip_selected);
            checkPosition = 1;
        });

        viewUnlockedVipSelect.setOnClickListener(v -> {
            context.startActivity(new Intent(context, SelectActivity.class));
        });

        viewUnlockedVipInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mobile = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        viewUnlockedVipButton.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(mobile) && mobile.length() == 11) {
                onVipDialogListener.onClick(mobile,checkPosition);
            }
        });
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_unlocked_vip_dialog);
    }

    public void setSelectText(String name){
        viewUnlockedVipSelect.setText(name);
    }

    public void showDialog(OnVipDialogListener onVipDialogListener) {
        this.onVipDialogListener = onVipDialogListener;
        viewUnlockedVipSelect.setText(Constants.CONSTELLATION_DESC[AppDataManager.getInstance().getSelectConstellationIndex()]);
        viewUnlockedVipInput.setText("");
        showPopupWindow();
    }
}
