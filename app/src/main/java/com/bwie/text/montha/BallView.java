package com.bwie.text.montha;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 *   自定义小球操作页面
 */

public class BallView extends View {

    private int height;
    private int width;
    private float radius = 40;
    private boolean ball;

    public BallView(Context context) {
        this(context,null);
    }

    public BallView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     *  测量方法
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到控件的宽和高，主要根据Xml布局中给定的宽和高得到
        height = getHeight()/2;
        width = getWidth()/2;
    }
    /**
     * 绘制小球的方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初试画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        //画出圆形
        canvas.drawCircle(width,height,radius,paint);
    }
    /**
     *  小球的触摸监听
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            //按下
            case MotionEvent.ACTION_DOWN:
                //得到触摸的坐标
                float x = event.getX();
                float y = event.getY();
                //进行传值判断
                ball = isBall(x, y);
                Toast.makeText(getContext(), ball +"", Toast.LENGTH_SHORT).show();
                break;
            //移动
            case MotionEvent.ACTION_MOVE:
                if(ball){
                    width = (int)event.getX();
                    height = (int)event.getY();




                    //进行重绘
                    postInvalidate();
                }
                break;
            //松开
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        //表示消费该事件
        return true;
    }
    /**
     *  判断点击是否在院内
     */
    private boolean isBall(float x,float y){
        float sqrt = (float) Math.sqrt((x - width) * (x - width) + (y - height) * (y - height));
        if(sqrt<=radius){
            return true;
        }
        return false;
    }
}