package com.example.gili.simplegraphlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import java.util.List;

/**
 * Created by gili on 30/07/2016.
 */
public class XYchart extends AbstractGraph {

    private Values values;

    /*************************constructors**************************/
    public XYchart(Context context) {
        super(context, null, 0);
        values = new PointsList();
    }

    public XYchart(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        values = new PointsList();
    }

    public XYchart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        values = new PointsList();
    }
    /***************************************************************/

    @Override
    protected void onDraw(Canvas canvas){

        super.drawGrid(canvas);

        if(!values.isEmpty()) {
            values.setPaintColor(valueLineColor);
            values.setTextSize(axisDigitsSize);
            values.draw(canvas, gridRange);
        }
    }

    /***********************API public methods*******************/

    //add new x,y value by the class point
    public void addValue(Point p) throws NullPointerException{

        if(p == null)
            throw new NullPointerException("Error - trying to add Null Point");

        else {
            values.addVal(p);
            setMaxYTxtSize(values.getMaxYTxt());

            invalidate();
        }
    }

    //add new x,y value
    public void addValue(float x, float y){

        Point newPoint = new Point(x, y);
        addValue(newPoint);

        invalidate();
    }

    //set new data, add until the shortest length between x and y
    public void setData(float[] x, float[] y){

        ((PointsList) values).setData(x, y);

        invalidate();
    }

    //add data, add until the shortest length between x and y
    public void addData(float[] x, float[] y){

        ((PointsList) values).addData(x, y);

        invalidate();
    }

    //get all points collection
    public List<Point> getAllPoints(){
        return ((PointsList) values).getPoints();
    }

    //remove specific point
    public void removeValue(Point p){
        ((PointsList) values).removePoint(p);

        invalidate();
    }

    //remove specific point
    public void removeValue(float x, float y){
        ((PointsList) values).removePoint(x, y);

        invalidate();
    }

    //remove collection of points
    public void removeValues(float[] x, float[] y){
        ((PointsList) values).removeValues(x, y);

        invalidate();
    }

    //remove all data
    public void clearData(){
        values.clearData();

        invalidate();
    }

    /***********configuration setting***********/
    public void setGridLineColor(int color){
        super.setGridLineColor(color);
    }

    public void setBackgrounColor(int color){
        super.setBackground(color);
    }

    public void setLabelsColor(int color){
        super.setLabelsColor(color);
    }
}
