package com.augeo.dotopia.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 4/29/2014.
 */
public class DoTopiaShadowButton extends RelativeLayout implements View.OnTouchListener {
    private Paint mPrimaryPaint;
    private Paint mSecondaryPaint;
    private Paint mPressedPaint;
    private Paint mPrimaryDisabledPaint;
    private Paint mSecondaryDisabledPaint;
    private int mPrimaryColor;
    private int mSecondaryColor;
    private int mPrimaryDisabledColor;
    private int mSecondaryDisabledColor;
    private int mPressedColor;
    private float mTextSize;
    private int mTextColor;
    private String mButtonText;
    private float mPadding;
    private float mDensity;
    private boolean mPressed;
    private OnClickListener mClickListener;
    private boolean mEnabled;

    public DoTopiaShadowButton(Context context) {
        this(context, null);
    }

    public DoTopiaShadowButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.ShadowButtonTheme_shadowbuttontheme);
    }

    public DoTopiaShadowButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);
        TypedArray arr = null;
        try{
            arr = context.obtainStyledAttributes(attrs, R.styleable.ShadowButton);
            mPrimaryColor = arr.getColor(R.styleable.ShadowButton_primary_color, getResources().getColor(R.color.orange_button_primary));
            mSecondaryColor = arr.getColor(R.styleable.ShadowButton_secondary_color, getResources().getColor(R.color.orange_button_secondary));
            mPressedColor = arr.getColor(R.styleable.ShadowButton_pressed_color, getResources().getColor(R.color.orange_button_pressed));
            mButtonText = arr.getString(R.styleable.ShadowButton_text);
            mPadding = arr.getDimension(R.styleable.ShadowButton_button_top_bottom_padding, 10);
            mTextSize = arr.getDimension(R.styleable.ShadowButton_text_size, 20f);
            mTextColor = arr.getColor(R.styleable.ShadowButton_text_color, getResources().getColor(R.color.dotopia_white));
            mEnabled = arr.getBoolean(R.styleable.ShadowButton_enabled, true);
            mPrimaryDisabledColor = arr.getColor(R.styleable.ShadowButton_disabled_primary_color, getResources().getColor(R.color.grey_button_primary));
            mSecondaryDisabledColor = arr.getColor(R.styleable.ShadowButton_disabled_secondary_color, getResources().getColor(R.color.grey_button_secondary));
        }finally {
            if(arr != null) {
                arr.recycle();
            }
        }
        mDensity = getResources().getDisplayMetrics().density;

        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setPadding(0, getPixelsFromDP(mPadding), 0, getPixelsFromDP(mPadding));
        setLayoutParams(params);
        setEnabled(mEnabled);

        init();
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
        if(mEnabled) {
            setOnTouchListener(this);
        }else {
            setOnTouchListener(null);
        }
        invalidate();
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    private int getPixelsFromDP(float value) {
        return ((int)((value * mDensity) + 0.5));
    }

    private void init() {
        mPrimaryPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondaryPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPressedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPrimaryDisabledPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondaryDisabledPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPrimaryDisabledPaint.setColor(mPrimaryDisabledColor);
        mSecondaryDisabledPaint.setColor(mSecondaryDisabledColor);
        mPrimaryPaint.setColor(mPrimaryColor);
        mSecondaryPaint.setColor(mSecondaryColor);
        mPressedPaint.setColor(mPressedColor);

        TextView tv = new TextView(getContext());
        tv.setText((!TextUtils.isEmpty(mButtonText))?mButtonText:"Button");
        RelativeLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv.setSingleLine(true);
        tv.setLayoutParams(params);
        tv.setTextSize(mTextSize);
        tv.setTextColor(mTextColor);
        addView(tv);
        invalidate();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mClickListener = l;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int wholeHeight = getHeight();
        int secondaryHeight = wholeHeight / 12;
        int primaryHeight = wholeHeight - secondaryHeight;
        if(mEnabled){
            if(!mPressed){
                canvas.drawRect(0f, 0f, getMeasuredWidth(), primaryHeight , mPrimaryPaint);
                canvas.drawRect(0f, primaryHeight, getMeasuredWidth(), getMeasuredHeight(), mSecondaryPaint);
            }else{
                canvas.drawRect(0f, 0f, getMeasuredWidth(), wholeHeight , mPressedPaint);
            }
        }else{
            canvas.drawRect(0f, 0f, getMeasuredWidth(), primaryHeight , mPrimaryDisabledPaint);
            canvas.drawRect(0f, primaryHeight, getMeasuredWidth(), getMeasuredHeight(), mSecondaryDisabledPaint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPressed = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPressed = false;
                invalidate();
                if(mClickListener != null) {
                    mClickListener.onClick(view);
                }
                break;
        }
        return true;
    }
}
