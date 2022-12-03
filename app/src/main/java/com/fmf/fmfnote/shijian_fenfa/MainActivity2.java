package com.fmf.fmfnote.shijian_fenfa;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fmf.fmfnote.R;

public class MainActivity2  extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        View viewById = findViewById(R.id.btn_barry);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Log.e("TAG","View－button的点击事件..");
              //  Toast.makeText(MainActivity2.this,"View－button的点击事件..",Toast.LENGTH_SHORT).show();
            }
        });



        viewById.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG","View－button的触摸事件..");
                //   Toast.makeText(MainActivity2.this,"View－button的触摸事件..",Toast.LENGTH_SHORT).show();
               //返回true   消费了事件, view 的onTouch触摸事件内部有点击事件   所以view 点击事件无效  加上v.performClick(); 就可以解决此问题
                // 返回 false  view 不消费事件(传递事件)              view 的点击事件有效

                v.performClick();
                return true;
            }
        });



         // 二分查找只能对有序序
        int[]  arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};

          int  key = 3;
          int  value=   binSearch(arr,key);
           Log.e("tag","二分 "+value);




        String s = 26348+"";
        for (int i=s.length()-1;i>=0;i--)
           // System.out.print(s.charAt(i));
        Log.e("tag_翻转 ",s.charAt(i)+"");
    }

    private int binSearch(int[] arr, int key) {


        int mid  = (arr.length-1)/2;
          if(key==arr[mid]){
              return  mid;
          }

           int start=0;
           int end = arr.length-1;

          while(start<=end){
              mid= (end-start)/2+start;

              if(key<arr[mid]){ //左边
                 end = mid-1;

              }else if(key>arr[mid]){ //右边
                 start = mid+1;

              }else{
                  return  mid;
              }

          }


        return -1;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }






}















