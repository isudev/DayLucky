package com.zaotao.daylucky.app;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.zaotao.daylucky.R;
import com.zaotao.daylucky.view.activity.AppWebViewActivity;

/**
 * Description SpanTextUtils
 * Created by wangisu@qq.com on 12/30/20.
 */
public class SpanTextUtils {
    public static void setAppPrivacyContent(Context context, String content, TextView textView) {
        SpannableString spannableStrReply = new SpannableString(content);
        String str = spannableStrReply.toString();
        int firstStart = str.indexOf("《用户");
        int firstEnd = str.indexOf("议》");
        int secondStart = str.indexOf("《隐私");
        int secondEnd = str.indexOf("策》");
        ClickableSpan clickableSpanFirst = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                AppWebViewActivity.startAppWebViewActivity(context, "http://daymark.zaotaoo.com/#/", "用户协议");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(context, R.color.colorAccent));   //设置字体颜色
                ds.setUnderlineText(false);    //设置是否显示下划线
                ds.clearShadowLayer();   //阴影
            }
        };
        ClickableSpan clickableSpanSecond = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                AppWebViewActivity.startAppWebViewActivity(context, "http://daymark.zaotaoo.com/#/", "隐私政策");
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(context, R.color.colorAccent));   //设置字体颜色
                ds.setUnderlineText(false);    //设置是否显示下划线
                ds.clearShadowLayer();   //阴影
            }
        };
        spannableStrReply.setSpan(clickableSpanFirst, firstStart, firstEnd + 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStrReply.setSpan(clickableSpanSecond, secondStart, secondEnd + 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStrReply);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}