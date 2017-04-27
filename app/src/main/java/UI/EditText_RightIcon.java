package UI;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * Created by XZC on 2017/4/27.
 */

public class EditText_RightIcon extends EditText{
    private Drawable dRight;
    private Rect rBounds;

    public EditText_RightIcon(Context context) {
        super(context);
        initEditText();
    }

    public EditText_RightIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditText();
    }

    public EditText_RightIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditText();
    }

    private void initEditText() {
        setEditTextDrawble();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                EditText_RightIcon.this.setEditTextDrawble();
            }
        });
    }

    private void setEditTextDrawble() {
        if(getText().toString().length() == 0){
            setCompoundDrawables(null,null,null,null);
        }else {
            setCompoundDrawables(null,null,this.dRight,null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dRight = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if((this.dRight != null) && (event.getAction() == 1)){
            this.rBounds = this.dRight.getBounds();
            int i = (int) event.getRawX();
            if(i > getRight() - 3*this.rBounds.width()){
                setText("");
                event.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if( right != null ){
            this.dRight = right;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }
}
