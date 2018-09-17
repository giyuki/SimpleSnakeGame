package com.example.a86275.snakegamebyycc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by 86275 on 2018/2/19.
 */

public class GameButton extends View {
    public  int ButtonSize=60;//dp为单位
    private Paint paint;
    private String gameButtonText;
    private Rect rect;
    public GameButton(Context context, AttributeSet attrs){
        super(context, attrs);
        ButtonSize=(int)(ButtonSize*context.getResources().getDisplayMetrics().density+0.5f);//转为px单位
        //Log.d(TAG, Float.toString(context.getResources().getDisplayMetrics().density)+"ButtonSize"+ButtonSize+"-"+ButtonSize/2);
        getAttrs(context,attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rect=new Rect();
    }
    private void getAttrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myViewDefinedAttr);
        gameButtonText = typedArray.getString(R.styleable.myViewDefinedAttr_GameButtonText);
        typedArray.recycle();//自定义view添加属性http://blog.sina.com.cn/s/blog_5a6f39cf0101cfin.html
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        paint.setARGB(100,20,20,20);
        canvas.drawCircle(ButtonSize/2,ButtonSize/2,ButtonSize/2,paint);
        paint.setColor(Color.YELLOW);
        paint.setTextSize((float)ButtonSize/gameButtonText.length()+20);//加个常数以免字过小
        paint.getTextBounds(gameButtonText, 0, gameButtonText.length(), rect);//获取,这一句rect不初始化会导致stopped！！！！！！！！！
        float textWidth = rect.width();
        float textHeight = rect.height();

        canvas.drawText(gameButtonText, getWidth() / 2 - textWidth / 2, getHeight() / 2
                + textHeight / 2, paint);
      //canvas.drawText(gameButtonText,0,ButtonSize,paint);
        Log.d(TAG, Integer.toString(getWidth())+getHeight());

    }

}
