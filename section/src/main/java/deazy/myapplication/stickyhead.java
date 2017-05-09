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
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by XZC on 2017/5/8.
 */

public class stickyhead extends FrameLayout {
    private mStickyHeaderlistview mStickyHeaderlistview ;
    private int totalWidth;
    private mAdapter adapter;
    private int headerHeight;

    public stickyhead(Context context) {
        super(context);
        init(context);
    }

    public stickyhead(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public stickyhead(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mStickyHeaderlistview = new mStickyHeaderlistview(context);
//        mStickyHeaderlistview.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT
//        ));
        addView(mStickyHeaderlistview);
        mStickyHeaderlistview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.e("哈哈", "onScroll: ");
                ;
            }
        });

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalWidth = w;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View childview = getChildAt(0);
//        Log.e(TAG, "onLayout: "+l+";"+t+";"+r+";"+b+";");
        childview.layout(0,0,r,b);
    }
    public void setadapter(mAdapter adapter){
        this.adapter = adapter;
        mStickyHeaderlistview.setAdapter(adapter);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
        headerHeight = adapter.getheight();
        canvas.save();
        drawHeaders(canvas);

        Rect clip = canvas.getClipBounds();
        clip.top = headerHeight ;

        canvas.clipRect(clip);

        super.dispatchDraw(canvas);
        canvas.restore();

    }

    private void drawHeaders(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(new Rect(0,0,totalWidth,headerHeight),paint);
    }

}
