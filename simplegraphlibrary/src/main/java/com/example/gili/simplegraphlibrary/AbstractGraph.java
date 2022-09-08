package com.example.gili.simplegraphlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.graphics.Canvas;
import android.widget.TextView;

/**
 * Created by gili on 28/07/2016.
 */
abstract class AbstractGraph extends RelativeLayout {

    private final int margin = 50;
    private final int padding = 30;
    private final int maxLineWidth = 12, maxTxtSize = 40;
    private Paint paint;
    private int gridLineWidth, gridLineColor, background, labelsColor;
    private int maxYTxtSize;
    private String bottomTitle, leftTitle;
    private TextView bottom_txt, left_txt;

    protected Rect gridRange = new Rect();
    protected int axisDigitsSize;
    protected int digitsColor, valueLineColor;


    /********************************constructors***********************************/
    public AbstractGraph(Context context) {
        this(context, null, 0);
    }

    public AbstractGraph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWillNotDraw(false);

        paint = new Paint();

        /*****************************attributes configurations*********************************/

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.XYchart);
        gridLineWidth = attributes.getDimensionPixelSize(R.styleable.XYchart_gridLineWidth, 1);
        gridLineColor = attributes.getInt(R.styleable.XYchart_gridLineColor, Color.BLUE);
        bottomTitle = attributes.getString(R.styleable.XYchart_bottomTitleText);
        leftTitle = attributes.getString(R.styleable.XYchart_leftTitleText);
        axisDigitsSize = attributes.getDimensionPixelSize(R.styleable.XYchart_axisDigitsSize, 10);
        digitsColor = attributes.getInt(R.styleable.XYchart_axisDigitsColor, Color.BLUE);
        valueLineColor = attributes.getInt(R.styleable.XYchart_valueLineColor, Color.BLUE);
        background = attributes.getInt(R.styleable.XYchart_backgroundColor, Color.BLACK);
        labelsColor = attributes.getInt(R.styleable.XYchart_labelsColor, Color.BLUE);

        if(axisDigitsSize > maxTxtSize)
            axisDigitsSize = maxTxtSize;

        if(gridLineWidth > maxLineWidth){
            gridLineWidth = maxLineWidth;
        }

        addLabels(context);
    }
    /****************************************************************************/

    /******************set settings*****************/
    protected void setGridLineColor(int color){
        gridLineColor = color;

        invalidate();
    }

    protected void setBackground(int color){
        background = color;

        invalidate();
    }

    protected void setLabelsColor(int color){
        labelsColor = color;

        invalidate();
    }

    protected void setMaxYTxtSize(int size){

        gridRange.left -= maxYTxtSize;
        gridRange.left += size;

        maxYTxtSize = size;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int gridLeft = 2*margin + gridLineWidth + maxYTxtSize;
        int gridTop = margin;
        int gridRight = getWidth() - margin;
        int gridBottom = getHeight() - (margin + padding + gridLineWidth + axisDigitsSize);

        gridRange.set(gridLeft, gridTop, gridRight, gridBottom);
    }

    //add left and bottom labels
    private void addLabels(Context context){

        //bottom title text
        LinearLayout bL = new LinearLayout(context);
        bL.setOrientation(LinearLayout.VERTICAL);
        LayoutParams bottomLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, margin + padding);
        bottomLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bottomLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        bL.setId(R.id.bL);
        bL.setLayoutParams(bottomLayoutParams);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;

        bottom_txt = new TextView(context);
        bottom_txt.setLayoutParams(params);
        bottom_txt.setTextColor(labelsColor);
        bottom_txt.setText(bottomTitle);
        bL.addView(bottom_txt);
        this.addView(bL);

        //left title text
        LinearLayout lL = new LinearLayout(context);
        lL.setOrientation(LinearLayout.HORIZONTAL);
        LayoutParams leftLayoutParams = new LayoutParams(margin, LayoutParams.MATCH_PARENT);
        leftLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        bL.setId(R.id.lL);
        lL.setLayoutParams(leftLayoutParams);

        LinearLayout.LayoutParams leftparams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftparams.weight = 1.0f;
        leftparams.gravity = Gravity.CENTER;

        left_txt = new TextView(context);
        left_txt.setLayoutParams(leftparams);
        left_txt.setTextColor(labelsColor);
        left_txt.setText(leftTitle);
        lL.addView(left_txt);
        this.addView(lL);
    }

    //draw the graph on @arg canvas
    protected void drawGrid(Canvas canvas){

        bottom_txt.setTextColor(labelsColor);
        left_txt.setTextColor(labelsColor);
        setBackgroundColor(background);
        paint.setStrokeWidth(gridLineWidth);
        paint.setColor(gridLineColor);
        canvas.drawLine(gridRange.left, gridRange.top, gridRange.left, gridRange.bottom - gridLineWidth, paint);
        canvas.drawLine(gridRange.left, gridRange.bottom - gridLineWidth, gridRange.right, gridRange.bottom - gridLineWidth, paint);
    }

    //abstract methods
    @Override
    protected abstract void onDraw(Canvas canvas);
}
