package UI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import deazy.myapp.R;

/**
 * Created by XZC on 2017/4/27.
 */

public class point_view extends View {

    private int totalWidth;
    private int totalHeight;
    private float mCurrentValue = 0;
    private int radius = 10;;
    private int Gap_Width = 50;
    private RectF rect = null;
    private boolean isStepOne = true;
    private static final String TAG = "point_view";
    private int contrast_x = 0;
    private int contrast_y = 0;
    private int waiting = 0;
    private int mPoint_Color = Color.WHITE;



    public point_view(Context context) {
        super(context);
    }

    public point_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);

    }



    public point_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.point_view);
        mPoint_Color = ta.getColor(R.styleable.point_view_Point_Color,Color.WHITE);
        radius = ta.getInteger(R.styleable.point_view_Point_Radius,10);
        Gap_Width = ta.getInteger(R.styleable.point_view_Point_GapWidth,50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(totalWidth/2,totalHeight/2);


        float[] pos_left = new float[2];


//        Path path = new Path();
//        position();
        if( waiting > 1 ) {
            waiting -= 1;
            draw_Three_Point(canvas);
        }else {
            mCurrentValue += 0.05+(0.5-Math.abs(mCurrentValue - 0.5))*0.1;
            if( mCurrentValue > 1 ){
                mCurrentValue = 0;
                isStepOne = !isStepOne;
                waiting = 12 ;
            }

            if (isStepOne) {
                pos_left = position(canvas, mCurrentValue, true);

                contrast_x = (int) (-(Gap_Width + pos_left[0]));
                contrast_y = (int) (-pos_left[1]);

                drawCricle(canvas, Gap_Width, 0);
            } else {
                pos_left = position(canvas, mCurrentValue, false);

                contrast_x = (int) (Gap_Width - pos_left[0]);
                contrast_y = (int) (-pos_left[1]);

                drawCricle(canvas, -Gap_Width, 0);
            }
        }

        drawCricle(canvas, contrast_x, contrast_y);
        drawCricle(canvas, (int) pos_left[0], (int) pos_left[1]);
        invalidate();
    }

    private void draw_Three_Point(Canvas canvas) {
        drawCricle(canvas, -Gap_Width, 0);
        drawCricle(canvas, 0, 0);
        drawCricle(canvas, Gap_Width, 0);
    }

    private float[] position(Canvas canvas,float currentValue,boolean isleft) {
        float[] pos = new float[2];
        float[] tan = new float[2];

        Path path = new Path();
        if(isleft) {
            rect = new RectF(-Gap_Width, -Gap_Width/2, 0, Gap_Width/2 );
        }else{
            rect = new RectF(0, -Gap_Width/2,  Gap_Width, Gap_Width/2 );
        }

        path.moveTo( Gap_Width,0);
        path.arcTo(rect,-180,180,true);

//        path.addArc(rect,0,180);
//        path.addArc();

//        Paint paint = new Paint();
//        paint.setColor(Color.BLACK);
//        canvas.drawPath(path,paint);


        PathMeasure measure = new PathMeasure(path,false);
//        Log.e(TAG, "position: "+measure.getLength() );
        measure.getPosTan(measure.getLength()*currentValue,pos,tan);
        return pos;
    }

    private void drawCricle(Canvas canvas,int X,int Y) {

        Path path = new Path();
        path.addCircle(X,Y, radius, Path.Direction.CW);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(mPoint_Color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawPath(path ,paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalWidth = w;
        totalHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
