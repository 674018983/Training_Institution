package deazy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SectionIndexer;

/**
 * Created by XZC on 2017/5/7.
 */
public class SideBar extends View {

    private final char[] letters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private Paint paint;//绘制字母的画笔
    private SectionIndexer sectionIndexer;
    private ListView mListView;
    private int drawWidth;//要绘制的单个字母的宽度
    private int drawHeight;//要绘制的单个字母的高度
    private int focusedIndex = -1;//点击选中的索引

    //监听当前点击的字母的接口，供外部实现
    private OnTouchChangedListener listener;
    public void setOnTouchChangedListener(OnTouchChangedListener listener) {
        this.listener = listener;
    }
    public interface OnTouchChangedListener {
        void onTouchDown(char c);
        void onTouchUp();
    }

    public void setListView(ListView mListView) {
        this.mListView = mListView;
        sectionIndexer = (SectionIndexer) mListView.getAdapter();
    }

    public SideBar(Context context) {
        super(context);
        init();
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        //设置字母的画笔属性
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setTextSize(18);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        drawWidth = getMeasuredWidth() / 2;//宽度为整个SideBar宽度的一半
        drawHeight = getMeasuredHeight() / letters.length;//高度为整个SideBar高度除以索引的个数
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < letters.length; i++) {
            canvas.drawText(String.valueOf(letters[i]), drawWidth, drawHeight + (i * drawHeight), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerY = (int) event.getY();
        //点击的y坐标 / 整体高度 * 数组的长度 = 数组的某一索引
        int selectedIndex = pointerY / drawHeight;
        if (selectedIndex >= letters.length) {
            selectedIndex = letters.length - 1;
        } else if (selectedIndex < 0) {
            selectedIndex = 0;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //点击时设置半透明的背景色
                setBackgroundColor(Color.parseColor("#33000000"));
            case MotionEvent.ACTION_MOVE:
                if (sectionIndexer == null) {
                    sectionIndexer = (SectionIndexer) mListView.getAdapter();
                }
                //根据数组中的元素获取对应的组位置
                int position = sectionIndexer.getPositionForSection(letters[selectedIndex]);
                if (position == -1) {
                    return true;
                }
                //改变当前ListView的所处的位置
                mListView.setSelection(position);
                //重绘SideBar
                invalidate();

                //供外部实现，监听当前点击的字母
                if (null != listener) {
                    listener.onTouchDown(letters[selectedIndex]);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //松开手指时取消背景色
                setBackgroundResource(android.R.color.transparent);
                //重绘SideBar
                invalidate();

                if (null != listener) {
                    listener.onTouchUp();
                }
                break;
        }

        return true;
    }

}