package com.cn.scrolllayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


/**
 * Created by yunzhao.liu on 2017/7/10
 */

public class ScreenUtils {
    private static int SCREEN_WIDTH = 0;
    private static int SCREEN_HEIGHT = 0;


    /**
     * 获取屏幕高度
     */
    public static int getScreenWidth(Context context) {
        return getScreenWidth(context, false);
    }

    public static int getScreenWidth(Context context, boolean cached) {
        if (!cached && SCREEN_WIDTH > 0) return SCREEN_WIDTH;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        if (cached) {
            return dm.widthPixels;
        }
        SCREEN_WIDTH = dm.widthPixels;
        return SCREEN_WIDTH;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenHeight(Context context) {
        return getScreenHeight(context, false);
    }

    public static int getScreenHeight(Context context, boolean cached) {
        if (!cached && SCREEN_HEIGHT > 0) return SCREEN_HEIGHT;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        if (cached) {
            return dm.heightPixels;
        }
        SCREEN_HEIGHT = dm.heightPixels;
        return SCREEN_HEIGHT;
    }

    /**
     * 获取通知栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static float dpToPx(Context context, float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getApplicationContext().getResources().getDisplayMetrics().density;
    }

    public static float pxToDp(Context context, float px) {
        if (context == null) {
            return -1;
        }
        return px / context.getApplicationContext().getResources().getDisplayMetrics().density;
    }

//    public static int dpToPxInt(float dp) {
//        return (int) (dpToPx(BaseApplication.Companion.getInstance(), dp) + 0.5f);
//    }

    public static int dpToPxInt(Context context, float dp) {
        return (int) (dpToPx(context, dp) + 0.5f);
    }

    public static int pxToDpInt(Context context, float px) {
        return (int) (pxToDp(context, px) + 0.5f);
    }

    /**
     * 根据手机的分辨率从弘洋适配的px转成为当前屏幕下宽的px
     */
    public static int auto2pxWidth(Context context, int autoValue) {
        return autoValue*getScreenWidth(context)/750;
    }

    /**
     * 根据手机的分辨率从弘洋适配的px转成为当前屏幕下高的px
     */
    public static int auto2pxHeight(Context context, int autoValue) {
        return autoValue*getScreenHeight(context)/1334;
    }

}
