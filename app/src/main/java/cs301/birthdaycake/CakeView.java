package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class CakeView extends SurfaceView {

    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();

    Paint Orange = new Paint();

    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f; // width of candle
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cake;



    /**
     * ctor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFC755B5);  //violet-red
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        Orange.setColor(Color.rgb(255, 165, 0));

        setBackgroundColor(Color.WHITE);  //better than black default

        cake = new CakeModel();

    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if(cake.candles && cake.numCandles > 0) {
            float spacing = cakeWidth/(cake.numCandles+1);
            for(int i = 1; i <= cake.numCandles; i++) {
                canvas.drawRect(spacing * i + candleWidth, bottom - candleHeight, spacing * i + candleWidth * 2, bottom, candlePaint);

                if (cake.lit) {
                    //draw the outer flame
                    float flameCenterX = spacing * i + candleWidth + candleWidth / 2;
                    float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                    canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                    //draw the inner flame
                    flameCenterY += outerFlameRadius / 3;
                    canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
                }


                //draw the wick
                float wickLeft = spacing*i + candleWidth + candleWidth / 2 - wickWidth/2;
                float wickTop = bottom - wickHeight - candleHeight;
                canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);
            }
        }


        if (cake.hasTouched) {
            canvas.drawRect(cake.x, cake.y, cake.x + 50, cake.y + 50, candlePaint);
            canvas.drawRect(cake.x, cake.y, cake.x - 50, cake.y - 50, candlePaint);
            canvas.drawRect(cake.x, cake.y, cake.x + 50, cake.y - 50, Orange);
            canvas.drawRect(cake.x, cake.y, cake.x - 50, cake.y + 50, Orange);
        }
    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;

        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now a candle in the center
        drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);

    }//onDraw


    public CakeModel getModel(){
        return cake;
    }//getModel

}//class CakeView

