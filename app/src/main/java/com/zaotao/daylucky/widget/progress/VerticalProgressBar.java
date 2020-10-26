package com.zaotao.daylucky.widget.progress;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zaotao.daylucky.R;


public class VerticalProgressBar extends View {
    private Paint mFinishPaint;// 画笔
    private float mProgress;// 进度值

    private float max;// 进度值
    private int width;// 宽度值
    private int height;// 高度值
    private final int default_finished_color = Color.rgb(252, 152, 12);
    private final int default_unfinished_color = Color.rgb(204, 204, 204);
    private int mFinishColor;
    private int mUnFinishColor;
    private float default_max = 100;
    private Paint mUnFinishPaint;
    private boolean mHasLine;
    private boolean mHasText;


    public VerticalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initPaint();
    }

    public VerticalProgressBar(Context context) {
        this(context, null);
        initPaint();
    }

    public VerticalProgressBar(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.VerticalProgressBar, defStyleAttr, 0);
        initByAttributes(attributes);
        attributes.recycle();
        initPaint();
    }

    /**
     * @desc 初始化自定义属性
     */
    private void initByAttributes(TypedArray attributes) {
        //设置完成进度条颜色
        mFinishColor = attributes.getColor(R.styleable.VerticalProgressBar_vpt_finished_color, default_finished_color);
        //设置未完成进度条颜色
        mUnFinishColor = attributes.getColor(R.styleable.VerticalProgressBar_vpt_unfinished_color, default_unfinished_color);
        //设置是否有边框
        mHasLine = attributes.getBoolean(R.styleable.VerticalProgressBar_vpt_hasline, false);
        //设置是否有字体
        mHasText = attributes.getBoolean(R.styleable.VerticalProgressBar_vpt_hasText, false);
        setMax(attributes.getFloat(R.styleable.VerticalProgressBar_vpt_max, default_max));
        setProgress(attributes.getFloat(R.styleable.VerticalProgressBar_vpt_progress, 0));

    }

    private void initPaint() {
        mFinishPaint = new Paint();
        mFinishPaint.setColor(mFinishColor);// 设置完成进度画笔颜色

        mUnFinishPaint = new Paint();
        mUnFinishPaint.setColor(mUnFinishColor);// 设置未完成进度画笔颜色

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth() - 1;// 宽度值
        height = getMeasuredHeight() - 1;// 高度值
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0, 0, width, height,
                30, 30, mUnFinishPaint);// 画未完成圆角矩形
        canvas.drawRoundRect(0, height - mProgress / max * height, width, height,
                30, 30, mFinishPaint);// 画完成圆角矩形

        if (mHasLine) {

            canvas.drawLine(0, 0, width, 0, mFinishPaint);// 画顶边
            canvas.drawLine(0, 0, 0, height, mFinishPaint);// 画左边
            canvas.drawLine(width, 0, width, height, mFinishPaint);// 画右边
            canvas.drawLine(0, height, width, height, mFinishPaint);// 画底边
        }

        super.onDraw(canvas);
    }

    /**
     * 拿到文字宽度
     *
     * @param
     */
    /*private int getTextWidth(String str) {
        // 计算文字所在矩形，可以得到宽高
        Rect rect = new Rect();
        mTextPaint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }*/
    public float getMax() {
        return max;
    }

    /**
     * 设置progressbar进度最大值
     */
    public void setMax(float max) {
        if (max > 0) {
            this.max = max;
            postInvalidate();
        }
    }

    public void setProgress(float progress) {
        setProgress(progress, true);
    }

    /**
     * 设置progressbar进度
     */
    public void setProgress(float progress, boolean needAnimation) {
        if (needAnimation) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mProgress, progress);
            valueAnimator.setDuration(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mProgress = (float) animation.getAnimatedValue();
                    if (mProgress > getMax()) {
                        mProgress %= getMax();
                    }
                    postInvalidate();
                }
            });
            valueAnimator.start();
        } else {
            mProgress = progress;
            if (mProgress > getMax()) {
                mProgress %= getMax();
            }
            postInvalidate();
        }
    }

    /**
     * 设置是否有边框
     */
    public void setHasLine(boolean hasLine) {
        mHasLine = hasLine;
        postInvalidate();
    }

    public void setFinishColor(int finishColor) {
        mFinishColor = finishColor;
        if (mFinishPaint != null) {
            mFinishPaint.setColor(mFinishColor);// 设置完成进度画笔颜色
        }
    }

    public void setUnFinishColor(int unFinishColor) {
        mUnFinishColor = unFinishColor;
        if (mUnFinishPaint != null) {
            mUnFinishPaint.setColor(mUnFinishColor);// 设置未完成进度画笔颜色
        }
    }
}
