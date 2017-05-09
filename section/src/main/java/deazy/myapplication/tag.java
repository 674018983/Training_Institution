package deazy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by XZC on 2017/5/7.
 */

public class tag extends ViewGroup {
    private static final String TAG = "tag";
    public tag(Context context) {
        super(context);
        init();
    }

    public tag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public tag(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TextView te = new TextView(getContext());
        te.setText("213454");
        te.setBackgroundResource(R.drawable.ba);
        addView(te);
        Log.e(TAG, "init: 11" );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "init: 12" );

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "init: 13" );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childview = getChildAt(0);
        Log.e(TAG, "onLayout: "+l+";"+t+";"+r+";"+b+";");
        childview.layout(0,0,300,300);

    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//
//        canvas.save();
//        Rect clip = canvas.getClipBounds();
//        clip.top = 200 ;
//        Paint mPaint =new Paint();
//        mPaint.setColor(Color.BLUE);
//
//        canvas.clipRect(clip);
//        super.dispatchDraw(canvas);
////        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
//        canvas.restore();
//    }
}
