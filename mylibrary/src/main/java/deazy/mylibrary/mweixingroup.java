package deazy.mylibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by XZC on 2017/4/12.
 */

public class mweixingroup extends ViewGroup{
    private Context context;
    public mweixingroup(Context context) {
        super(context);
        this.context=context;
        invalidate();
    }

    public mweixingroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

}
