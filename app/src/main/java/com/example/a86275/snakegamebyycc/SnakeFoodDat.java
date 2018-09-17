package com.example.a86275.snakegamebyycc;

import java.util.Random;

/**
 * Created by 86275 on 2018/2/20.
 */

public class SnakeFoodDat {
    public SnakeFoodDat(){
        Random random=new Random();
        Foodx=random.nextInt(GameDisplay.GamePixelX);
        Foody=random.nextInt(GameDisplay.GamePixelY);
    }
    int Foodx;
    int Foody;
    public void setFoodXY(int x,int y){
        Foodx=x;
        Foody=y;
    }
    public void foodUpdate(){
        Random random=new Random();
        Foodx=random.nextInt(GameDisplay.GamePixelX);
        Foody=random.nextInt(GameDisplay.GamePixelY);
    }
}
