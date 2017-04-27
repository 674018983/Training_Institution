package deazy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * Created by XZC on 2017/4/27.
 */

public class point_view extends View {

    private int totalWidth;
    private int totalHeight;

    public point_view(Context context) {
        super(context);
    }

    public point_view(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public point_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        RotateAnimation
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
