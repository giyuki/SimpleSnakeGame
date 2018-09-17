package com.example.a86275.snakegamebyycc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="mytag";
    SnakePosDat snakePosDat;
    SnakeFoodDat snakeFoodDat;
    GameDisplay gameDisplay;
    private Handler handler =new Handler(){
        public void handleMessage(Message msg){
            switch(msg.what){
                case 1:gameDisplay.invalidate();
                    break;
                case 2:gameDisplay.invalidate();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameDisplay=(GameDisplay) findViewById(R.id.gameDisplayView);
        snakePosDat=gameDisplay.getSnakePosDat();
        snakeFoodDat=gameDisplay.getSnakeFoodDat();
        GameButton gameButton1=(GameButton) findViewById(R.id.gameButton1);
        GameButton gameButton2=(GameButton) findViewById(R.id.gameButton2);
        GameButton gameButton3=(GameButton) findViewById(R.id.gameButton3);
        GameButton gameButton4=(GameButton) findViewById(R.id.gameButton4);
        GameButton gameButton5=(GameButton) findViewById(R.id.gameButton5);
        gameButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakePosDat.snakeMov(snakePosDat.getX(0),snakePosDat.getY(0)+1);
                snakePosDat.setdir(1);
                if(snakePosDat.getX(0)==snakeFoodDat.Foodx&&snakePosDat.getY(0)==snakeFoodDat.Foody){
                    snakeFoodDat.foodUpdate();
                    snakePosDat.putNewNode();
                }
                gameDisplay.invalidate();
            }
        });
        gameButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakePosDat.reStart();
                gameDisplay.invalidate();
            }
        });
        gameButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakePosDat.snakeMov(snakePosDat.getX(0),snakePosDat.getY(0)-1);
                snakePosDat.setdir(0);
                if(snakePosDat.getX(0)==snakeFoodDat.Foodx&&snakePosDat.getY(0)==snakeFoodDat.Foody){
                    snakeFoodDat.foodUpdate();
                    snakePosDat.putNewNode();
                }
                gameDisplay.invalidate();
            }
        });
        gameButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakePosDat.snakeMov(snakePosDat.getX(0)-1,snakePosDat.getY(0));
                snakePosDat.setdir(2);
                if(snakePosDat.getX(0)==snakeFoodDat.Foodx&&snakePosDat.getY(0)==snakeFoodDat.Foody){
                    snakeFoodDat.foodUpdate();
                    snakePosDat.putNewNode();
                }
                gameDisplay.invalidate();
            }
        });
        gameButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snakePosDat.snakeMov(snakePosDat.getX(0)+1,snakePosDat.getY(0));
                snakePosDat.setdir(3);
                if(snakePosDat.getX(0)==snakeFoodDat.Foodx&&snakePosDat.getY(0)==snakeFoodDat.Foody){
                    snakeFoodDat.foodUpdate();
                    snakePosDat.putNewNode();
                }
                gameDisplay.invalidate();
            }
        });
        class MyThread extends Thread{
            @Override
            public void run(){
                while(true){
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                    snakePosDat.snakeKeepMov();
                    if(snakePosDat.getX(0)==snakeFoodDat.Foodx&&snakePosDat.getY(0)==snakeFoodDat.Foody){
                        snakeFoodDat.foodUpdate();
                        Message message2=new Message();
                        message2.what=2;
                        handler.sendMessage(message2);
                        snakePosDat.putNewNode();
                    }
                    try {
                        MyThread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "run: ");
                    }
                }
            }
        }
        new MyThread().start();
    }
}
