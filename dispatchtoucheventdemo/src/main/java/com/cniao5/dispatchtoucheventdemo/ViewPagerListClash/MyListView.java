package com.cniao5.dispatchtoucheventdemo.ViewPagerListClash;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 内部拦截法：子view(MyListView)处理事件冲突
 *  */
public class MyListView extends ListView {
   public MyListView(Context context) {
        super(context);
    }
  public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*  private int mLastX, mLastY;
  @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
     //todo   当down时,父容器(BadViewPager)会把事件往下分发给子view(父容器拿不到该事件了)  让子view(MyListVIew)来处理事件(子view 拿到down事件 )
           getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
   // todo    如果Math.abs(deltaX) > Math.abs(deltaY)  判断出是左右滑动,
    // todo 那么   子view 把该事件  让给父容器(BadViewPager)去处理      让 父容器会拦截事件,父容器处理该事件  那么你就可以左右滑动父容器(BadViewPager)
                  if (Math.abs(deltaX) > Math.abs(deltaY)) {
                 getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;

            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }*/
 }
