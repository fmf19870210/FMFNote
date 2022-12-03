package com.cniao5.dispatchtoucheventdemo.ViewPagerListClash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;


/**
    外部拦截法：父容器处理冲突事件
 *  父容器想要把事件分发给谁就分发给谁
 *     */
public class BadViewPager extends ViewPager {

    private int mLastX, mLastY;

    public BadViewPager(@NonNull Context context) {
        super(context);
    }

    public BadViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            super.onInterceptTouchEvent(event);
//            return false;
//        }
//        return true;

        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
   //todo  如果  onInterceptTouchEvent  =false 父容器(BadViewPager)不拦截事件 ,   分发给子view (MyListView )     父容器(BadViewPager) 不处理事件(那么父容器就不能左右滑动了)
                //    return false;
  //todo  如果  onInterceptTouchEvent  =true 父容器(BadViewPager)拦截事件 ，不分发给子view (MyListView ) 父容器(BadViewPager) 自己处理事件(那么父容器就可以左右滑动了)
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        return super.onInterceptTouchEvent(event);

    }
}
