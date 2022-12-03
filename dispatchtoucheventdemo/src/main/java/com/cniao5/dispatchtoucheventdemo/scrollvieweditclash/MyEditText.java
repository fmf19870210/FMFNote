package com.cniao5.dispatchtoucheventdemo.scrollvieweditclash;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 *  一 个 ScrollView 里嵌着一个 EditText（设置可滑动）。
 *  问题：mScrollView 可滑动，但里面设置的 mEditText 不可滑动。
 *  原因分析:mScrollView把事件消费掉了，没有传递到EditText，从而导致没法使得 EditText 响应事件
 *  解决思路:
       当触摸的是EditText & 当前EditText可滚动时，则将事件交给EditText处理，让EditText进行滚动
 *    让EMyEditText实现OnTouch 的事件监听setOnTouchListener(OnTouchListener l) 重写 onTouch()方法,
 *    调用v.getParent().requestDisallowInterceptTouchEvent(true)
 *      disallowIntercept = true  请求父亲禁用事件拦截的功能,viewGroup.onInterceptTouchEvent(ev)=false，父亲不会拦截事件,
       当触摸的是EditText & 当前EditText可滚动时，则将该滑动事件交给MyEditText处理；
 *
 *
 *
 *     否则将事件交由其父类处理，即交给ScrollView进行滚动
 *
 *

 *
 * */
public class MyEditText extends androidx.appcompat.widget.AppCompatEditText implements View.OnTouchListener {
    public MyEditText(Context context) {
        super(context);
        setOnTouchListener(this);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
    }

    /**
     *  复写EditText的onTouch方法
     * */

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       if(canVerticalScroll()){
           // 当触摸的是EditText & 当前EditText可滚动时，则将事件交给EditText处理；
           v.getParent().requestDisallowInterceptTouchEvent(true);
       }else{
       // 否则将事件交由其父类处理
           v.getParent().requestDisallowInterceptTouchEvent(false);
       }

        return false;
    }


    // 判断当前EditText是否可滚动
    private boolean canVerticalScroll() {
       if (getLineCount() >getMaxLines()) {
           return true;
       }
        return false;
    }


}
