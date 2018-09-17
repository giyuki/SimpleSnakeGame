package com.example.a86275.snakegamebyycc;

import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.id.list;
import static android.R.string.no;
import static android.content.ContentValues.TAG;
import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by 86275 on 2018/2/20.
 */

public class SnakePosDat {
    private int snakeLength;
    private int dir=0;//上下左右依次为0，1，2，3
    private ArrayList<Node> list=new ArrayList<Node>();
    public SnakePosDat(){
        int x,y;
        snakeLength=0;
        Random random=new Random();
        x=random.nextInt(GameDisplay.GamePixelX-10)+5;
        y=random.nextInt(GameDisplay.GamePixelY-10)+5;
        newNode(x,y);
        newNode(x+1,y);
        newNode(x+2,y);
        newNode(x+3,y);
    }
    private class Node{
        int NodeX=0;
        int NodeY=0;
    }
    public int newNode(int x,int y){
        Node node=new Node();
        Node node1;
        node.NodeX=x;
        node.NodeY=y;
        list.add(node);
        node1=list.get(snakeLength);
        if(!(node.NodeY==node1.NodeY&&node.NodeX==node1.NodeX))
            Log.e(TAG, "node!=node1" );
        snakeLength+=1;
        return snakeLength;
    }
    public void putNewNode(){
        if(getdir()==0)
            newNode(getX(getLength()-1),getY(getLength()-1)-1);
        else if(getdir()==1)
            newNode(getX(getLength()-1),getY(getLength()-1)+1);
        else if(getdir()==2)
            newNode(getX(getLength()-1)-1,getY(getLength()-1));
        else if(getdir()==3)
            newNode(getX(getLength()-1)+1,getY(getLength()-1));
    }
    public int getX(int index){
        Node node=list.get(index);
        return node.NodeX;
    }
    public int getY(int index){
        Node node=list.get(index);
        return node.NodeY;
    }
    public int getLength(){
        return snakeLength;
    }
    public void snakeMov(int x,int y){
        Node node=new Node();
        node.NodeX=x;
        node.NodeY=y;
        list.add(0,node);
        list.remove(snakeLength);
    }
    public void setdir(int d){
        dir=d;
        if(dir<0||dir>3){
            Log.e(TAG, "Wrongdir");
        }
    }
    public void snakeKeepMov(){
        if(dir==0){
            snakeMov(getX(0),getY(0)-1);
        }else if(dir==1){
            snakeMov(getX(0),getY(0)+1);
        }else if(dir==2){
            snakeMov(getX(0)-1,getY(0));
        }else if(dir==3){
            snakeMov(getX(0)+1,getY(0));
        }
    }
    public void reStart(){
        list.clear();
        int x,y;
        snakeLength=0;
        Random random=new Random();
        x=random.nextInt(GameDisplay.GamePixelX-10)+5;
        y=random.nextInt(GameDisplay.GamePixelY-10)+5;
        newNode(x,y);
        newNode(x+1,y);
        newNode(x+2,y);
        newNode(x+3,y);
    }
    public int getdir(){
        return dir;
    }
    /*public void setXY(int nodeNum,int x,int y){
        Node node=list.get(nodeNum);
        node.NodeX=x;
        node.NodeY=y;
    }*/
}
