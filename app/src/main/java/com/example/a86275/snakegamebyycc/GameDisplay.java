package com.example.a86275.snakegamebyycc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.R.attr.x;
import static android.R.attr.y;
import static android.content.ContentValues.TAG;

/**
 * Created by 86275 on 2018/2/19.
 */

public class GameDisplay extends View {
    public static final int WallWidth=30;
    public static final int GamePixelX=15;//x方向游戏像素个数，就是能放蛇节点的个数 不能小于10
    public static final int GamePixelY=24;
    private int SnakeNodePixelX;
    private int SnakeNodePixelY;
    private Paint paint;
    private Rect rect;
    public SnakePosDat snakePosDat;
    public SnakeFoodDat snakeFoodDat;
    //两个最重要的类就是Canvas(画布)和Paint(画笔)，通过Canvas我们可以设置
    //绘制的形状和路径，当然仅仅形状和路径是不行的，还需要颜色啊，阴影啊，透明度等等的设置，这时候就是Paint的事
    public GameDisplay(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        snakePosDat=new SnakePosDat();
        snakeFoodDat=new SnakeFoodDat();
    }
    @Override
    protected void onDraw(Canvas canvas){
        SnakeNodePixelX=(int)((float)(getWidth()-WallWidth*2)/GamePixelX+0.5f);
        SnakeNodePixelY=(int)((float)(getHeight()-WallWidth*2)/GamePixelY+0.5f);
        rect = new Rect(WallWidth/2,WallWidth/2,getWidth()-WallWidth/2,getHeight()-WallWidth/2);//一个保存矩形左上和右下的xy坐标的类 调用getWidth一定要注意一开始view还没创建完返回值是0
        Log.d(TAG, "SnakeNodePixel"+SnakeNodePixelX+"-"+SnakeNodePixelX);
        canvas.drawRGB(120,120,120);
        paint.setARGB(100,20,20,20);
        paint.setStrokeWidth(WallWidth);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect,paint);//画矩形两个坐标是线的中间连成的交点作为坐标点
        for(int i=0;i<snakePosDat.getLength();i++)
            drawSnakeNode(canvas,snakePosDat.getX(i),snakePosDat.getY(i));
        drawFood(canvas,snakeFoodDat.Foodx,snakeFoodDat.Foody);
    }
    private void drawSnakeNode(Canvas canvas,int x,int y){
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        rect = new Rect(SnakeNodePixelX*x+WallWidth,SnakeNodePixelY*y+WallWidth,SnakeNodePixelX*(x+1)-1+WallWidth,SnakeNodePixelY*(y+1)-1+WallWidth);
        canvas.drawRect(rect,paint);

        paint.setARGB(50,10,10,20);
        paint.setStrokeWidth(SnakeNodePixelX/4);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rect,paint);
    }
    private void drawFood(Canvas canvas,int x,int y){
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(SnakeNodePixelX*(x+0.5f)+WallWidth,SnakeNodePixelY*(y+0.5f)+
                WallWidth,SnakeNodePixelX/2,paint);

        paint.setARGB(30,10,10,20);
        paint.setStrokeWidth(SnakeNodePixelX/6);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(SnakeNodePixelX*(x+0.5f)+WallWidth,SnakeNodePixelY*(y+0.5f)+
                WallWidth,SnakeNodePixelX/2,paint);
    }
    public SnakePosDat getSnakePosDat(){
        return snakePosDat;
    }
    public SnakeFoodDat getSnakeFoodDat(){
        return snakeFoodDat;
    }
}
