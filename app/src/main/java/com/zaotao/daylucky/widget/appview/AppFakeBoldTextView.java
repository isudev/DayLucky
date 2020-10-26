package com.zaotao.daylucky.widget.appview;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Description FakeBoldTextView
 * Created by wangisu@qq.com on 2019-12-12
 */
public class AppFakeBoldTextView extends AppCompatTextView {

    public AppFakeBoldTextView(Context context) {
        super(context);
        init(context);
    }

    public AppFakeBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AppFakeBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        getPaint().setFakeBoldText(true);
    }
}
