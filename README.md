# 地图滑动列表 #

## **[ 博客地址具体讲解https://blog.csdn.net/liu_yunzhao/article/details/80387158 ](https://blog.csdn.net/liu_yunzhao/article/details/80387158)** ##

## 示例演示 ##
![](https://github.com/liuyunzhao/ScrollLayout/blob/master/gif/map.gif) ![](https://github.com/liuyunzhao/ScrollLayout/blob/master/gif/map2.gif)

## **[Demo下载 ](https://github.com/liuyunzhao/ScrollLayout/blob/master/gif/app-debug.apk)** ##

## 简单使用 ##
### xml布局 ###
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.cn.scrolllayout.MainActivity">

    <ImageView
        android:id="@+id/map"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/map"
        android:scaleType="centerCrop"/>

    <com.cn.scrolllayout.view.ScrollLayout
        android:id="@+id/scroll_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#00000000"
        android:fillViewport="true">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:orientation="horizontal">

            <Button
                android:id="@+id/news"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#0f0"
                android:gravity="center"
                android:text="新闻"
                android:textColor="#000"
                android:textSize="20sp"/>

            <Button
                android:id="@+id/video"
                android:layout_width="0dp"
                android:layout_height="match_parent"
                android:layout_weight="1"
                android:background="#888"
                android:gravity="center"
                android:text="视频"
                android:textColor="#fff"
                android:textSize="20sp"/>
        </LinearLayout>

        <com.cn.scrolllayout.view.ScrollRecyclerView
            android:id="@+id/scroll_list"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#ffffff"/>

        <com.cn.scrolllayout.view.ScrollTextView
            android:id="@+id/scroll_bottom"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            android:background="#ffffff"
            android:gravity="center"
            android:text="上滑展开更多列表"
            android:textColor="#000000"
            android:textSize="12dp"
            android:visibility="gone"/>
    </com.cn.scrolllayout.view.ScrollLayout>

    <RelativeLayout
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:background="@color/colorPrimary"
        android:visibility="invisible">

        <ImageView
            android:layout_width="60dp"
            android:layout_height="match_parent"
            android:padding="10dp"
            android:src="@drawable/back"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_centerHorizontal="true"
            android:gravity="center"
            android:text="ScrollLayout"
            android:textColor="#fff"
            android:textSize="19sp"/>
    </RelativeLayout>
</FrameLayout>

```
### MainActivity部分代码 ###

```
private void initView() {
		.......
        initClick();
        mTitle.setBackgroundColor(Color.argb(0, 63, 81, 181));
        mScrollLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

private void initClick() {
        mScrollLayout.setOnScrollChangedListener(new ScrollLayout.OnScrollChangedListener() {
            @Override
            public void onScrollChange(int status) {
                scrollLayouChange(status);
            }

            @Override
            public void onScrollProgress(int progress) {
                if (progress > 0) {
                    mTitle.setVisibility(View.VISIBLE);
                } else {
                    mTitle.setVisibility(View.INVISIBLE);
                }
                mTitle.setBackgroundColor(Color.argb(progress, 63, 81, 181));
                mScrollLayout.setBackgroundColor(Color.argb(progress, 0, 0, 0));
            }
        });


        mScrollTextView.setOnTextViewListener(new ScrollTextView.OnTextViewListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.toggle(ScrollLayout.STATUS_DEFAULT);
            }
        });
    }

private void scrollLayouChange(int status) {
        mScrollTextView.setVisibility(status == ScrollLayout.STATUS_CLOSE
                ? View.VISIBLE : View.GONE);
    }

```