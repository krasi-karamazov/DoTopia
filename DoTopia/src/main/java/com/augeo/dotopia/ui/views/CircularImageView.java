package com.augeo.dotopia.ui.views;

/**
 * Created by krasimir.karamazov on 4/29/2014.
 */
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.augeo.dotopia.R;

/**
 * Created by krasimir.karamazov on 4/14/2014.
 */
public class CircularImageView extends ImageView {

    private Paint mMainPaint;
    private Paint mBorderPaint;
    private Bitmap mImage;
    private int mBorderWidth;
    private int mCanvasSize;
    private Bitmap mBitmap;
    private boolean mCircular;

    public CircularImageView(Context context) {
        this(context, null);
        init();
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.circularImageViewStyle);
        init();
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        TypedArray arr = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView);
        if(arr.getBoolean(R.styleable.CircularImageView_shadow, false)){
            //addShadow();
        }

        setBorderWidth(arr.getInt(R.styleable.CircularImageView_border_width, 5));
        setBorderColor(arr.getInt(R.styleable.CircularImageView_border_color, Color.WHITE));
        arr.recycle();
    }

    private void addShadow() {
        //setLayerType(LAYER_TYPE_SOFTWARE, mBorderPaint);
        if(mBorderPaint != null) {
            mBorderPaint.setShadowLayer(10.0f, 5.0f, 5.0f, Color.WHITE);
        }
        invalidate();
    }

    public void setBorderWidth(int borderWidth) {
        this.mBorderWidth = borderWidth;
        requestLayout();
        invalidate();
    }

    public void setBorderColor(int color) {
        if(mBorderPaint != null) {
            mBorderPaint.setColor(color);
        }
        invalidate();
    }

    public void toggleCircular(boolean circular) {
        mCircular = circular;
        invalidate();
    }

    private void init() {
        mMainPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = calculateMeasuredWidth(widthMeasureSpec);
        int measuredHeight = calculateMeasuredHeight(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int calculateMeasuredWidth(int widthSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(widthSpec);
        int specSize = MeasureSpec.getSize(widthSpec);

        if(specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        }else if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }else{
            result = mCanvasSize;
        }

        return result;
    }

    private int calculateMeasuredHeight(int heightSpec){
        int result = 0;
        int specMode = MeasureSpec.getMode(heightSpec);
        int specSize = MeasureSpec.getSize(heightSpec);

        if(specMode == MeasureSpec.AT_MOST) {
            result = specSize;
        }else if(specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }else{
            result = mCanvasSize;
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        BitmapDrawable bmpDrawable = (BitmapDrawable)getDrawable();

        if(bmpDrawable != null) {
            Bitmap image = bmpDrawable.getBitmap();

            mImage = Bitmap.createScaledBitmap(image, getMeasuredWidth(), getMeasuredHeight(), false);

            if(image != null) {
                mCanvasSize = mImage.getWidth();
                if(mCanvasSize > mImage.getHeight()) {
                    mCanvasSize = mImage.getHeight();
                }

                BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(mImage, mCanvasSize, mCanvasSize, false), BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
                mMainPaint.setShader(shader);
                int circleCenter = (mCanvasSize - (mBorderWidth * 2)) / 2;
                canvas.drawCircle(circleCenter + mBorderWidth, circleCenter + mBorderWidth, ((mCanvasSize - (mBorderWidth * 2)) / 2) + mBorderWidth - 4.0f, mBorderPaint);
                canvas.drawCircle(circleCenter + mBorderWidth, circleCenter + mBorderWidth, ((mCanvasSize - (mBorderWidth * 2)) / 2) - 4.0f, mMainPaint);
            }
        }
    }
}

