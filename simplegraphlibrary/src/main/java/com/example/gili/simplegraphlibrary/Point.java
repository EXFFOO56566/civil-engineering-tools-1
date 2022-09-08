package com.example.gili.simplegraphlibrary;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by gili on 28/07/2016.
 */
public class Point {

    //values
    private float x;
    private float y;

    //constructor
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    //get x value
    public float getX(){
        return x;
    }

    //get y value
    public float getY(){
        return y;
    }

    //check if equals to @arg other according to x value
    public boolean equals(Point other){
        return (this.x == other.x);
    }

    //draw the point on @arg canvas
    public void draw(Canvas canvas, Paint paint){

        canvas.drawCircle(x, y, 20f, paint);
    }
}
