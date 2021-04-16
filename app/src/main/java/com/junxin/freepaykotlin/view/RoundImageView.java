package com.junxin.freepaykotlin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.junxin.freepaykotlin.R;


public class RoundImageView extends AppCompatImageView {

    private float width, height;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, int defultRadiusDp) {
        this(context, null);
        this.radius = dip2px(context, defultRadiusDp);
    }

    public RoundImageView(Context context, float defultRadius) {
        this(context, null);
        this.radius = defultRadius;
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        getAttribute(context, attrs, defStyleAttr);
    }

    private float radius = 12;

    private void getAttribute(Context context, AttributeSet attrs, int defStyleAttr) {
        //获取自定义属性。
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        if (typedArray != null) {
            radius = typedArray.getDimension(R.styleable.RoundImageView_round_img_radius, 12);
            typedArray.recycle();
            return;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (width > radius && height > radius) {
            Path path = new Path();
            path.moveTo(radius, 0);
            path.lineTo(width - radius, 0);
            path.quadTo(width, 0, width, radius);
            path.lineTo(width, height - radius);
            path.quadTo(width, height, width - radius, height);
            path.lineTo(radius, height);
            path.quadTo(0, height, 0, height - radius);
            path.lineTo(0, radius);
            path.quadTo(0, 0, radius, 0);
            canvas.clipPath(path);
        }

        super.onDraw(canvas);
    }

    public void setRadius(float radiusPx) {
        this.radius = radius;
        this.requestLayout();
    }

    private int dip2px(Context context, int dp) {
        float pxDimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return (int) pxDimension;
    }

}