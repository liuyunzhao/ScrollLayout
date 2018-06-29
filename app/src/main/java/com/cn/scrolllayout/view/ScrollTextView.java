package com.cn.scrolllayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by yunzhao.liu on 2018/6/20
 */

public class ScrollTextView extends android.support.v7.widget.AppCompatTextView {

    private boolean isClickValid = true;
    private int touchSlop;
    private float dX, dY;

    public ScrollTextView(Context context) {
        this(context, null);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 重写了此方法后，就不能再设置onClickListener事件了
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eX = event.getX();
        float eY = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = eX;
                dY = eY;
                isClickValid = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                if (isClickValid
                        && (Math.abs(eY - dY) > touchSlop
                        || Math.abs(eY - dY) > Math.abs(eX - dX))) {
                    isClickValid = false;
                }
                return isClickValid;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //这里的目的是模拟一个点击事件
                if (isClickValid && listener != null) {
                    listener.onClick(this);
                }
                return isClickValid;
        }
        return super.onTouchEvent(event);
    }

    private OnTextViewListener listener;

    public interface OnTextViewListener {
        void onClick(View v);
    }

    public void setOnTextViewListener(OnTextViewListener l) {
        this.listener = l;
    }

}
