package com.zaotao.daylucky.widget.ringview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.zaotao.daylucky.R;

/**
 * Description RingChartFortuneView
 * Created by wangisu@qq.com on 12/23/20.
 */
public class RingChartView extends View {
    public final static int TYPE_RECT = 0;
    public final static int TYPE_CIRCLE = 1;
    public final static int TYPE_ROUND_RECT = 2;
    public final static int TOTAL_DURATION = 1000;
    private final static int PENDING_VALUE_NOT_SET = -1;
    /*rect_progress member*/
    RectF mBgRect;
    RectF mProgressRect;
    /*common member*/
    private int mWidth;
    private int mHeight;
    private int mType;
    private int mProgressColor;
    private int mBackgroundColor;
    private int mMaxValue;
    private int mValue;
    private int mPendingValue;
    private long mAnimationStartTime;
    private int mAnimationDistance;
    private int mAnimationDuration;
    private boolean mRoundCap;
    private Paint mBackgroundPaint = new Paint();
    private Paint mPaint = new Paint();
    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private RectF mArcOval = new RectF();
    private String mText = "";
    private int mStrokeWidth;
    private int mCircleRadius;
    private Point mCenterPoint;


    public RingChartView(Context context) {
        super(context);
        setup(context);
    }

    public RingChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public RingChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    public void setup(Context context) {
        mType = TYPE_CIRCLE;
        mProgressColor = ContextCompat.getColor(context, R.color.colorAccent);
        mBackgroundColor = ContextCompat.getColor(context, R.color.colorFAFAFA);

        mMaxValue = 100;
        mValue = 0;

        mRoundCap = false;
        if (mType == TYPE_CIRCLE) {
            final float scale = context.getResources().getDisplayMetrics().density;
            mStrokeWidth = (int) (18 * scale + 0.5f);
        }
        configPaint(mRoundCap);

        setProgressNum(mProgressColor, mValue);
    }

    private void configShape() {
        if (mType == TYPE_RECT || mType == TYPE_ROUND_RECT) {
            mBgRect = new RectF(getPaddingLeft(), getPaddingTop(), mWidth + getPaddingLeft(), mHeight + getPaddingTop());
            mProgressRect = new RectF();
        } else {
            mCircleRadius = (Math.min(mWidth, mHeight) - mStrokeWidth) / 2;
            mCenterPoint = new Point(mWidth / 2, mHeight / 2);
        }
    }

    private void configPaint(boolean isRoundCap) {
        mPaint.setColor(mProgressColor);
        mBackgroundPaint.setColor(mBackgroundColor);
        if (mType == TYPE_RECT || mType == TYPE_ROUND_RECT) {
            mPaint.setStyle(Paint.Style.FILL);
            mBackgroundPaint.setStyle(Paint.Style.FILL);
        } else {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(mStrokeWidth);
            mPaint.setAntiAlias(true);
            if (isRoundCap) {
                mPaint.setStrokeCap(Paint.Cap.ROUND);
            }
            mBackgroundPaint.setStyle(Paint.Style.STROKE);
            mBackgroundPaint.setStrokeWidth(mStrokeWidth);
            mBackgroundPaint.setAntiAlias(true);
        }
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void setType(int type) {
        mType = type;
        configPaint(mRoundCap);
        invalidate();
    }

    public void setBarColor(int backgroundColor, int progressColor) {
        mBackgroundColor = backgroundColor;
        mProgressColor = progressColor;
        mBackgroundPaint.setColor(mBackgroundColor);
        mPaint.setColor(mProgressColor);
        invalidate();
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        mBackgroundColor = backgroundColor;
        mBackgroundPaint.setColor(mBackgroundColor);
        invalidate();
    }

    public void setProgressColor(int progressColor) {
        mProgressColor = progressColor;
        mPaint.setColor(mProgressColor);
        invalidate();
    }

    /**
     * 设置环形进度条的两端是否有圆形的线帽，类型为{@link #TYPE_CIRCLE}时生效
     */
    public void setStrokeRoundCap(boolean isRoundCap) {
        mPaint.setStrokeCap(isRoundCap ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mPendingValue != PENDING_VALUE_NOT_SET) {
            long elapsed = System.currentTimeMillis() - mAnimationStartTime;
            if (elapsed >= mAnimationDuration) {
                mValue = mPendingValue;
                mPendingValue = PENDING_VALUE_NOT_SET;
            } else {
                mValue = (int) (mPendingValue - (1f - ((float) elapsed / mAnimationDuration)) * mAnimationDistance);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        if (((mType == TYPE_RECT || mType == TYPE_ROUND_RECT) && mBgRect == null) ||
                (mType == TYPE_CIRCLE && mCenterPoint == null)) {
            // npe protect, sometimes measure may not be called by parent.
            configShape();
        }
        if (mType == TYPE_RECT) {
            drawRect(canvas);
        } else if (mType == TYPE_ROUND_RECT) {
            drawRoundRect(canvas);
        } else {
            drawCircle(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mHeight = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        configShape();
        setMeasuredDimension(mWidth, mHeight);
    }

    private void drawRect(Canvas canvas) {
        canvas.drawRect(mBgRect, mBackgroundPaint);
        mProgressRect.set(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + parseValueToWidth(), getPaddingTop() + mHeight);
        canvas.drawRect(mProgressRect, mPaint);
        if (mText != null && mText.length() > 0) {
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            float baseline = mBgRect.top + (mBgRect.height() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(mText, mBgRect.centerX(), baseline, mTextPaint);
        }
    }

    private void drawRoundRect(Canvas canvas) {
        float round = mHeight / 2f;
        canvas.drawRoundRect(mBgRect, round, round, mBackgroundPaint);
        mProgressRect.set(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + parseValueToWidth(), getPaddingTop() + mHeight);
        canvas.drawRoundRect(mProgressRect, round, round, mPaint);
        if (mText != null && mText.length() > 0) {
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            float baseline = mBgRect.top + (mBgRect.height() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(mText, mBgRect.centerX(), baseline, mTextPaint);
        }
    }

    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mCenterPoint.x, mCenterPoint.y, mCircleRadius, mBackgroundPaint);
        mArcOval.left = mCenterPoint.x - mCircleRadius;
        mArcOval.right = mCenterPoint.x + mCircleRadius;
        mArcOval.top = mCenterPoint.y - mCircleRadius;
        mArcOval.bottom = mCenterPoint.y + mCircleRadius;
        if (mValue > 0) {
            canvas.drawArc(mArcOval, 270, 360f * mValue / mMaxValue, false, mPaint);
        }
        if (mText != null && mText.length() > 0) {
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            float baseline = mArcOval.top + (mArcOval.height() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
            canvas.drawText(mText, mCenterPoint.x, baseline, mTextPaint);
        }
    }

    private int parseValueToWidth() {
        return mWidth * mValue / mMaxValue;
    }

    public int getProgress() {
        return mValue;
    }

    public void setProgressNum(int progressColor, int progress) {
        this.mProgressColor = progressColor;
        mPaint.setColor(mProgressColor);
        setProgress(progress, true);
    }

    public void setProgress(int progress, boolean animated) {
        if (progress > mMaxValue) {
            progress = 100;
        }
        if (progress < 0) {
            progress = 0;
        }

        /*if ((mPendingValue == PENDING_VALUE_NOT_SET && mValue == progress) ||
                (mPendingValue != PENDING_VALUE_NOT_SET && mPendingValue == progress)) {
            return;
        }*/

        if (!animated) {
            mPendingValue = PENDING_VALUE_NOT_SET;
            mValue = progress;
            invalidate();
        } else {
            mAnimationDuration = Math.abs((int) (TOTAL_DURATION * (mValue - progress) / (float) mMaxValue));
            mAnimationStartTime = System.currentTimeMillis();
            mAnimationDistance = progress - mValue;
            mPendingValue = progress;
            invalidate();
        }
    }

    public int getMaxValue() {
        return mMaxValue;
    }

    public void setMaxValue(int maxValue) {
        mMaxValue = maxValue;
    }

}