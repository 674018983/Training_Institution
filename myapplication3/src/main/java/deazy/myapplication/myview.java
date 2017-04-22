package deazy.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by XZC on 2017/4/20.
 */

public class myview extends ViewGroup{

    private Context context;
    public myview(Context context) {
        super(context);
        init(context);
    }

    public myview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public myview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = 0;i < 4; i++){
            Button bt = new Button(context);
            addView(bt);
        }
        invalidate();
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new LinearLayout.LayoutParams(p);
    }
}
