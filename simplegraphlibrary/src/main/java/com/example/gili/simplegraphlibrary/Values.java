package com.example.gili.simplegraphlibrary;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


/**
 * Created by gili on 28/07/2016.
 */
abstract class Values {

    protected Paint datPaint;
    protected Paint tetxPaint;
    protected float maxVal;
    protected float minVal;
    protected int textSize;

    //constructor
    public Values(){
        datPaint = new Paint();
        tetxPaint = new Paint();
        datPaint.setColor(Color.WHITE);
        tetxPaint.setColor(Color.WHITE);
    }

    //get maximum value
    public float getMaxVal(){
        return maxVal;
    }

    //get minimum value
    public float getMinVal(){
        return minVal;
    }

    //set paint color
    public void setPaintColor(int color){
        datPaint.setColor(color);
    }

    //set the size of text
    public void setTextSize(int size){
        textSize = size;
    }

    /*********************abstracts methods**********************/
    protected abstract void draw(Canvas canvas, Rect range);
    protected abstract void addVal(Point p);
    protected abstract boolean isEmpty();
    protected abstract int getMaxYTxt();
    protected abstract void clearData();
}
