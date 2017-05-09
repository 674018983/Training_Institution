package deazy.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by XZC on 2017/5/8.
 */

public class mStickyHeaderlistview extends ListView {
    public mStickyHeaderlistview(Context context) {
        super(context);
    }

    public mStickyHeaderlistview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public mStickyHeaderlistview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }

}
