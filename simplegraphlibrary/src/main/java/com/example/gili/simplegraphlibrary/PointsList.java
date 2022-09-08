package com.example.gili.simplegraphlibrary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gili on 28/07/2016.
 */
class PointsList extends Values {

    private List<Point> points;

    //constructor
    public PointsList(){
        super();

        points = new ArrayList<Point>();
    }

    //get minimum point according to x value
    public float getMinX(){
        return points.get(0).getX();
    }

    //get maximum point according to x value
    public float getMaxX(){
        return points.get(points.size()-1).getX();
    }

    //get all points
    public List<Point> getPoints(){
        return points;
    }

    //set new data points
    public void setData(float[] x, float[] y){
        insertData(x, y, true);
    }

    //add data points
    public void addData(float[] x, float[] y){
        insertData(x, y, false);
    }

    //return true if there is no points
    @Override
    public boolean isEmpty(){
        return points.isEmpty();
    }

    //add new point
    @Override
    public void addVal(Point newPoint){

        if(newPoint == null)
            return;

        int location = 0;
        boolean containe = false;

        for (Point p : points) {

            //check that @parm newpoint is not contain at points list
            if(newPoint.equals(p)){
                containe = true;
                break;
            }

            //find the correct location for @parm newpoint
            if(newPoint.getX() > p.getX())
                location++;
        }

        if(!containe) {

            if(points.size() == 0){
                maxVal = newPoint.getY();
                minVal = newPoint.getY();
            }

            else if(newPoint.getY() > maxVal)
                maxVal = newPoint.getY();

            else if(newPoint.getY() < minVal)
                minVal = newPoint.getY();

            points.add(location, newPoint);
        }
    }

    //remove all points
    @Override
    public void clearData(){
        points.clear();
    }

    //remove specific point
    public void removePoint(Point rmvPoint){
        removePoint(rmvPoint.getX(), rmvPoint.getY());
    }

    //remove specific point
    public void removePoint(float x, float y){

        for (Point p : points){

            if(p.getX() == x && p.getY() == y){
                points.remove(p);
                break;
            }
        }
    }

    //remove point collection
    public void removeValues(float[] x, float[] y){

        int len = Math.min(x.length, y.length);

        for(int i = 0; i < len; i++)
            removePoint(x[i], y[i]);
    }

    @Override
    public void draw(Canvas canvas, Rect gridRange){

        int lenght = points.size();
        for(int i = 0; i < lenght; i++){

            Point p1 = dynamicTransformation(points.get(i), points.get(0), points.get(lenght-1), gridRange);

            if(lenght == 1)
                canvas.drawLine(gridRange.left, gridRange.bottom, p1.getX(), p1.getY(), datPaint);

            if(i < lenght - 1) {
                Point p2 = dynamicTransformation(points.get(i+1), points.get(0), points.get(lenght-1), gridRange);
                canvas.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), datPaint);
            }
            //draw the current point
           //p1.draw(canvas, datPaint);
        }

        drawTxt(canvas, gridRange);
    }

    //draw digits on x axis and y axis
    private void drawTxt(Canvas canvas, Rect gridRange){

        int length = points.size();
        int gridNumber = 5;
        int step = (gridRange.right - gridRange.left) / gridNumber;

        //draw digits
        float max = getMaxX();
        float min = getMinX();

        if(length == 1)
            min = 0;

        float dis = (max - min) / gridNumber;

        tetxPaint.setTextSize(textSize);


        //draw x digits
        for(int i = 0; i < gridNumber + 1; i++){

            String txt = Float.toString(round(min, 1));

            Rect bounds = getTxtBounds(tetxPaint, txt);
            canvas.drawText(txt, (gridRange.left + i*step) - (bounds.width()/2), gridRange.bottom + bounds.height(), tetxPaint);
            min += dis;
        }

        step = (gridRange.bottom - gridRange.top) / gridNumber;

        max = maxVal;
        min = minVal;

        if(length == 1)
            min = 0;

        dis = (max - min) / gridNumber;

        //draw y digits
        for(int i = 0; i < gridNumber + 1; i++){

            String txt = Float.toString(round(min, 1));

            Rect bounds = getTxtBounds(tetxPaint, txt);
            canvas.drawText(txt, gridRange.left - bounds.width(), gridRange.bottom - step*i, tetxPaint);
            min += dis;
        }
    }

    //get the maximum width of y axis digits
    public int getMaxYTxt(){

        int max = 0;

        for (Point p : points){
            Rect size = getTxtBounds(tetxPaint, Float.toString(p.getY()));

            if(max < size.width())
                max = size.width();
        }

        return max;
    }

    //get the bounds of @arg s according to specific paint @arg p
    private Rect getTxtBounds(Paint p, String s){

        if(s == null)
            return null;

        Rect bounds = new Rect();
        p.getTextBounds(s, 0, s.length(), bounds);

        return bounds;
    }

    //transmit dimension of @arg p according to exist values
    private Point dynamicTransformation(Point p, Point first, Point last, Rect gridRange){

        /**********************y axis transformation****************************/
        float newY;
        if(p.getY() == maxVal)
            newY = gridRange.top;

        else if(p.getY() == minVal)
            newY = gridRange.bottom;

        else{
            float y_ratio = (p.getY() - minVal) / (maxVal - p.getY());
            //to keep the correct proportion - y - maximum = r(minimum - y), after Simplification:
            newY = gridRange.bottom - (((y_ratio * gridRange.bottom) + gridRange.top) / (1 + y_ratio));
        }
        /**********************x axis transformation****************************/

        if(p.equals(last))
            return new Point(gridRange.right, newY);

        else if(p.equals(first))
            return new Point(gridRange.left, newY);

        else{
            float x_ratio = (p.getX() - first.getX()) / (last.getX() - p.getX());
            //to keep the correct proportion - x - left = r(right - x), after Simplification:
            float newX = ((x_ratio * gridRange.right) + gridRange.left) / (1 + x_ratio);
            return new Point(newX, newY);
        }
    }

    //add data if @arg clear=false or replace data if @arg clear=true
    private void insertData(float[] x, float[] y, boolean clear){

        if(clear)
            points.clear();

        int len = Math.min(x.length, y.length);

        for(int i = 0; i < len; i++){
            Point newPoint = new Point(x[i], y[i]);
            addVal(newPoint);
        }
    }

    //return float @arg d with @arg decimalPlace after the decimal point
    private float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);

        return bd.floatValue();
    }
}
