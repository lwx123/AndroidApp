package com.lwx.fish.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lwx.fish.myapplication.R;

/**
 * Created by wuxing on 2019/11/26.
 */
public class MyTextView extends View {

    private static final String TAG = MyTextView.class.getSimpleName();
    private String mText;
    private Paint paint;

    private int count = 0;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.test);
        mText = ta.getString(R.styleable.test_text);
        int textAttr = ta.getIndex(R.styleable.test_testAttr);
        Log.e(TAG, "text = " + mText + " , textAttr = " + textAttr);
        ta.recycle();


        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(80);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                mText = "你摸了我一下";
                //刷新画布
                postInvalidate();
            }
        });

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null || TextUtils.isEmpty(mText)){
            return;
        }
        canvas.drawText(mText,20,100,paint);
    }
}
