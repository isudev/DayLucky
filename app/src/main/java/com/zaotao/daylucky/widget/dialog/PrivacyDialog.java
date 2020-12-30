package com.zaotao.daylucky.widget.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.isuu.base.view.RoundTextView;
import com.zaotao.daylucky.R;
import com.zaotao.daylucky.app.AppDataManager;
import com.zaotao.daylucky.app.SpanTextUtils;

import razerdp.basepopup.BasePopupWindow;

/**
 * Description PrivacyDialog
 * Created by wangisu@qq.com on 12/30/20.
 */
public class PrivacyDialog extends BasePopupWindow {

    private View.OnClickListener onClickListener;
    private Context context;

    public PrivacyDialog(Context context) {
        super(context);
        this.context = context;
        init(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.view_privacy_dialog);
    }

    public void init(Context context) {
        setOutSideDismiss(false);
        TextView viewPrivacyHintText = findViewById(R.id.view_privacy_hint_text);
        RoundTextView viewPrivacyAgree = findViewById(R.id.view_privacy_agree);
        TextView viewPrivacyDisagree = findViewById(R.id.view_privacy_disagree);
        SpanTextUtils.setAppPrivacyContent(context, viewPrivacyHintText.getText().toString(), viewPrivacyHintText);
        viewPrivacyAgree.setOnClickListener(view -> {
            AppDataManager.getInstance().setPrivacyShowStatus();
            dismiss();
        });
        viewPrivacyDisagree.setOnClickListener(view -> {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
            dismiss();
        });
    }

    public void show(View.OnClickListener onAppStateListener) {
        this.onClickListener = onAppStateListener;
        if (!AppDataManager.getInstance().getPrivacyShowed()) {
            showPopupWindow();
        }
    }

    @Override
    public boolean onBackPressed() {
        if (AppDataManager.getInstance().getPrivacyShowed()) {
            return super.onBackPressed();
        } else {
            ((Activity) context).finish();
        }
        return false;
    }
}