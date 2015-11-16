package com.renkun.wallpaper.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.renkun.wallpaper.R;

/**
 * Created by rk on 2015/11/12.
 */
public class ThemeSelect extends View {
    private int colors;
    private Paint mPaint;
    public ThemeSelect(Context context) {
        this(context,null);
    }

    public ThemeSelect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThemeSelect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,  defStyleAttr);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.ThemeSelect);
        colors=typedArray.getColor(R.styleable.ThemeSelect_bg_color,Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthsize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightsize=MeasureSpec.getSize(heightMeasureSpec);
        int widht,heught;
        if (widthMode==MeasureSpec.EXACTLY){
            widht=widthsize;
        }else {

            widht=getPaddingLeft()+getPaddingRight();
        }
        if (heightMode==MeasureSpec.EXACTLY){
            heught=heightsize;
        }else {
            heught=getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(widht,widht);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint=new Paint();
        mPaint.setColor(colors);
        RectF r2=new RectF();                           //RectF对象
        r2.left=0;                                 //左边
        r2.top=0;                                 //上边
        r2.right=getWidth();                                   //右边
        r2.bottom=getWidth();                              //下边
        canvas.drawRoundRect(r2, getWidth()/6, getWidth()/6, mPaint);
    }
}
