package UI.WeiXinButton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XZC on 2017/4/12.
 */

public class WeiXinButton extends View {

    private int mItemId = 0;
    private List<Icon> mlist_Icon= new ArrayList<Icon>();
    private float last_position=0;
    private boolean toLeft=false;
    private int mItem_next = 1;
    private ViewPager mViewPager = null;
    public WeiXinButton(Context context) {
        super(context);
    }

    public WeiXinButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiXinButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘画选中选项
        set_Check(canvas);

    }


    public void add(List<Icon> mlist_Icon){
        this.mlist_Icon = mlist_Icon;
    }

    /**
     * 每个区域中图片的右边
     * */
    private int area_left_icon(int ItemId) {
        int area_width=getMeasuredWidth()/(mlist_Icon.size());
        Bitmap icon = BitmapFactory.decodeResource(getResources(), mlist_Icon.get(ItemId).getStartIcon());
        return (area_width/2+area_width*ItemId-icon.getWidth()/2);
    }
    /**
     * 每个区域中图片的上边
     * */
    private int area_top_icon(int ItemId) {
        int area_height=getMeasuredHeight();
        Bitmap icon = BitmapFactory.decodeResource(getResources(), mlist_Icon.get(ItemId).getStartIcon());
        return ((area_height-icon.getHeight())/2);
    }

    /**
     * 设置选中图片
     * */
    private void set_Check(Canvas canvas) {

        //验证mlist_Icon是否为空
        if(mlist_Icon == null ||(mlist_Icon.size() == 0)){
            throw new NullPointerException("add(list) shouldn't null");
        }

        Paint mpaint=new Paint();
        Paint mpaint_next=new Paint();
        Paint mpaint_other=new Paint();
        Paint mpaint_other2=new Paint();
        mpaint_other.setAlpha(0);
        mpaint_next.setAlpha(0);

        if(last_position != 0) {
            if (toLeft && mItemId != 0) {
                mpaint.setAlpha((int) (255 * last_position));
                mpaint_next.setAlpha((int) (255 * (1 - last_position)));
            } else {
                mpaint.setAlpha((int) (255 * (1 - last_position)));
                mpaint_next.setAlpha((int) (255 * last_position));
            }
        }


        //绘画出所有图片
        for (Icon micon:mlist_Icon) {
            Bitmap Start_icon = BitmapFactory.decodeResource(getResources(),micon.getStartIcon());
            Bitmap End_icon = BitmapFactory.decodeResource(getResources(),micon.getEndIcon());

            if(micon.Id == mItemId){
                //当前选中图片
                canvas.drawBitmap(Start_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint);
                canvas.drawBitmap(End_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint_next);
            }else if(micon.Id == mItem_next){
                //下一个即将装换图片
                canvas.drawBitmap(Start_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint_next);
                canvas.drawBitmap(End_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint);
            }
            else{
                //其他图片
                canvas.drawBitmap(Start_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint_other);
                canvas.drawBitmap(End_icon,area_left_icon(micon.Id),area_top_icon(micon.Id),mpaint_other2);
            }
        }

    }
    /**
     * 根据pagerview来设置的动画监听
     * */
    public void ViewPageListener(ViewPager viewpager){
        mViewPager = viewpager;
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float mypositionOffset = positionOffset;
                if(last_position == 0 ) {
                    //判断是往左边滑还是右边滑
                    if (mypositionOffset > 0.5) {
                        toLeft = true;
                        mItem_next = mItemId - 1;
                    } else {
                        toLeft = false;
                        mItem_next = mItemId + 1;
                    }
                }
                last_position = positionOffset;


                mItemId = position;

                //往左边移动跟往右边移动有点不同
                if(toLeft&&(last_position!=0)){
                    mItemId+=1;
                }

                invalidate();
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = 0;
        int y = 0;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :

                break;
            case MotionEvent.ACTION_UP:
                x = (int) event.getX();
                y = (int) event.getY();
                Log.e("阿萨德看风景", "onTouchEvent: "+getMeasuredWidth()+":"+getMeasuredHeight());
                Log.e("阿萨德看风景1", "onTouchEvent: "+x+":"+y);

                int area_width=getMeasuredWidth()/mlist_Icon.size();
                mItemId = x / area_width;
                mViewPager.setCurrentItem(mItemId);
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

}
