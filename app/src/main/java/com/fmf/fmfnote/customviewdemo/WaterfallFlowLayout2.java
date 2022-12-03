package com.fmf.fmfnote.customviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



/*
*  通过前面两节课我们知道了其实，绘制流程最终会调用到OnMesure  和   onLayout方法,分别进行各自自己的绘制和摆放
*
*
*
*
* */

public class WaterfallFlowLayout2  extends ViewGroup {
    public WaterfallFlowLayout2(Context context) {
        super(context);
    }

    public WaterfallFlowLayout2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterfallFlowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public WaterfallFlowLayout2(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }




  /*
  * onMeasure(int widthMeasureSpec, int heightMeasureSpec) 测量步骤
  * 1. 通过MeasureSpec 获取父类布局的 宽的模式和宽的大小值   和 高的模式和宽的大小值
  *  2.  在xml布局文件里  父布局宽高测量模式是EXACTLY(match_parent) , 就是父容器最大的精确大小数值= 子childView的宽高大小值
  *
  *
  * */



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       //获取父布局宽的模式和宽的大小值
        int widthMode_parent = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize_parent =MeasureSpec.getSize(widthMeasureSpec);

        //获取父布局 高的模式和高的大小值
        int heightMode_parent= MeasureSpec.getMode(heightMeasureSpec);
         int heightSize_parent=MeasureSpec.getSize(heightMeasureSpec);




          // 当前子childview的宽高大小值
        int  measureWidthSize_child;
        int measureHeightSzie_child;
        //在xml布局文件里  父布局宽高测量模式是EXACTLY(match_parent) , 就是父容器最大的精确大小数值= 子childView的宽高大小值
          if(widthMode_parent==MeasureSpec.EXACTLY&&heightMode_parent==MeasureSpec.EXACTLY){
                 measureWidthSize_child= widthSize_parent;
                measureHeightSzie_child=heightSize_parent;
              Log.i("barry","测量子view的布局大小值 "+measureWidthSize_child+"."+measureHeightSzie_child);

          } else{ // 在xml里面,父布局文件里宽高测量模式是ATMOST(wrap_content),那么父容器里的子childview自己测量自己的宽高大小值,不能超过父容器的大小


              //获取父布局里的所有的子控件view数量
               int childViewCount=getChildCount();


              List<View> viewList = new ArrayList<>();

              for(int i = 0;i < childViewCount ; i++){


              }



          }





    }




  /*
  *
  *
  *
  * */



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
