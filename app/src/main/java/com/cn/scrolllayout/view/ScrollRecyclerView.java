package com.cn.scrolllayout.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cn.scrolllayout.LogUtils;


/**
 * Created by yunzhao.liu on 2018/6/20
 */

public class ScrollRecyclerView extends RecyclerView {

    public static boolean canScroll = true;
    public float lastY;

    public ScrollRecyclerView(Context context) {
        this(context, null);
    }

    public ScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float eY = e.getY();
        switch (e.getAction()) {
            //因为是滚动列表，所以父View的dispatchTouchEvent不会调用Down事件，而是从Move事件开始调用
            case MotionEvent.ACTION_DOWN:
                LogUtils.i("liuyzz:recyclerview:", "---down");
                lastY = eY;
                canScroll = true;
                super.onTouchEvent(e);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (!canScroll) {
                    return false;
                }
                //下滑负  上滑正
                int offset = (int) (lastY - eY);
                lastY = eY;
                LogUtils.i("liuyzz:recyclerview:", ""+canScroll+"-isTop:"+isTop()+"-offset:"+offset);
                canScroll = !isTop() || offset > 0;
                super.onTouchEvent(e);//如果不写，则会不让滑动
                return canScroll;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                canScroll = true;
                super.onTouchEvent(e);
                return true;
        }
        return super.onTouchEvent(e);
    }

    private boolean isTop() {
        LayoutManager manager = getLayoutManager();
        if (manager == null
                || !(manager instanceof LinearLayoutManager)) {
            return false;
        }
        //显示区域最上面一条信息的position
        int visibleItemPosition = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
        View childView = getChildAt(0);//getChildAt(0)只能获得当前能看到的item的第一个
        LogUtils.i("liuyzz:recyclerview:", "position:"+visibleItemPosition+"-top:"+childView.getTop());
        //坐标系 下正右正
        //D/liuyzz:recyclerview:: position:0-top:-3
        //D/liuyzz:recyclerview:: position:0-top:-35
        //D/liuyzz:recyclerview:: position:3-top:-10
        //D/liuyzz:recyclerview:: position:3-top:-34
        return childView != null && visibleItemPosition <= 0 && childView.getTop() >= 0;

    }
}
