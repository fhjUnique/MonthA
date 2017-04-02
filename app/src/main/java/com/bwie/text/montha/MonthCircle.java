package com.bwie.text.montha;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dell on 2017/4/2.
 */

public class MonthCircle extends View {

    private Paint rectPaint;
    //区域
    private Region rectRegion;
    //路径
    private Path rectPath;

    private Paint circlePaint;
    //区域
    private Region circleRegion;
    //路径
    private Path circlePath;

    private Paint circleSmallPaint;
    //区域
    private Region circleSmallRegion;
    //路径
    private Path circleSmallPath;


    private Paint textPaint;



    private int with;
    private int height;
    private int cricle_color;
    private String textView;
    private int monthCircle_radius_big;
    private int radius_small;
    private int text_size;

    public MonthCircle(Context context) {
        super(context);
        initPaint();
    }


    public MonthCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MonthCircle);
        cricle_color = array.getColor(R.styleable.MonthCircle_cricle_color, Color.RED);
        textView = array.getString(R.styleable.MonthCircle_text_);
        monthCircle_radius_big = array.getInteger(R.styleable.MonthCircle_radius_big,400);
        radius_small = array.getInteger(R.styleable.MonthCircle_radius_small,300);
        text_size = array.getInteger(R.styleable.MonthCircle_text_size, 30);



    }
    // 初始化画笔
    private void initPaint() {
        rectPaint = new Paint();
        rectRegion = new Region();
        rectPath = new Path();

        circlePaint = new Paint();
        circleRegion = new Region();
        circlePath = new Path();

        circleSmallPaint = new Paint();
        circleSmallRegion = new Region();
        circleSmallPath = new Path();

        textPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        with = w;
        height = h;

        rectPath.addRect(w / 2 - 200, h / 2 - 200, w / 2 + 200, h / 2 + 200, Path.Direction.CW);
        // ▼将剪裁边界设置为视图大小
        Region globalRegion = new Region(-w, -h, w, h);
        // ▼将 Path 添加到 Region 中
        rectRegion.setPath(rectPath, globalRegion);


        circlePath.addCircle(w/2,h/2,monthCircle_radius_big, Path.Direction.CW);
        // ▼将剪裁边界设置为视图大小
        Region cricleRegion = new Region(-w, -h, w, h);
        // ▼将 Path 添加到 Region 中
        circleRegion.setPath(circlePath, cricleRegion);


        circleSmallPath.addCircle(w/2,h/2,radius_small, Path.Direction.CW);
        // ▼将剪裁边界设置为视图大小
        Region cricleSmallRegion = new Region(-w, -h, w, h);
        // ▼将 Path 添加到 Region 中
        circleSmallRegion.setPath(circleSmallPath, cricleSmallRegion);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectPaint.setColor(Color.GREEN);
        Path rect_path = rectPath;
        // 绘制矩形
        canvas.drawPath(rect_path, rectPaint);
        //大圆
        circlePaint.setColor(Color.YELLOW);
        Path cricle_path = circlePath;
        canvas.drawPath(cricle_path, circlePaint);

        //小圆
        circleSmallPaint.setColor(cricle_color);
        Path cricleSmall_path = circleSmallPath;
        canvas.drawPath(cricleSmall_path, circleSmallPaint);

        Rect rect = new Rect();
//        float text = textPaint.measureText(textView);
        textPaint.setTextSize(text_size);
//        Paint.FontMetrics metrics = textPaint.getFontMetrics();
        textPaint.getTextBounds(textView, 0, textView.length(), rect);
        canvas.drawText(textView,with/2-rect.width()/2,height/2+rect.height()/2,textPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                if(circleSmallRegion.contains(x,y)){
                    Toast.makeText(getContext(), "点击圆环", Toast.LENGTH_SHORT).show();
                }else if (circleRegion.contains(x,y)) {
                    Toast.makeText(getContext(), "点击大圆", Toast.LENGTH_SHORT).show();
                }else if(rectRegion.contains(x,y)){
                    Toast.makeText(getContext(), "点击矩形", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "点击矩形外", Toast.LENGTH_SHORT).show();
                }

                break;
        }

        return true;
    }
}

