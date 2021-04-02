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

import java.util.ArrayList;
import java.util.List;

/**
 * Description SpanTextUtils
 * Created by wangisu@qq.com on 12/30/20.
 */
public class SpanTextUtils {
    public static void setAppPrivacyContent(Context context, String content, TextView textView) {
        String userProtocol = textView.getContext().getResources().getString(R.string.title_bar_text_agreement);
        String privacyPolicy = textView.getContext().getResources().getString(R.string.title_bar_text_privacy);

        SpannableString sb = new SpannableString(content);

        List<String> highlights = new ArrayList<>();

        highlights.add("《" + userProtocol + "》");
        highlights.add("《" + privacyPolicy + "》");


        ClickableSpan clickableSpanFirst = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                AppWebViewActivity.startAppWebViewActivity(context, "http://daymark.zaotaoo.com/#/", userProtocol);
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
                AppWebViewActivity.startAppWebViewActivity(context, "http://daymark.zaotaoo.com/#/", privacyPolicy);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ContextCompat.getColor(context, R.color.colorAccent));   //设置字体颜色
                ds.setUnderlineText(false);    //设置是否显示下划线
                ds.clearShadowLayer();   //阴影
            }
        };

        for (int i = 0; i < highlights.size(); i++) {
            String highlight = highlights.get(i);
            int start = 0, end;
            int index;
            while ((index = content.indexOf(highlight, start)) > -1) {
                end = index + highlight.length();
                if (i == 0) {
                    sb.setSpan(clickableSpanFirst, index, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                } else if (i == 1) {
                    sb.setSpan(clickableSpanSecond, index, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                }
                start = end;
            }
        }

        textView.setText(sb);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}