package com.fmf.fmfnote.kotlin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/**
 * Create by Fangmingfei on 2022-11-14 下午 6:19
 * Describe ：
 */
public class MainJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        KotlinDemo demo = new KotlinDemo();
        demo.setValueCallback("哈哈", new Function0<Unit>() {
            @Override
            public Unit invoke() {
               //  Toast.makeText(this,"我被回调了！！",Toast.LENGTH_LONG).show();
                Log.e("","我被回调了！！");
                 return null;
            }
        });


        demo.setValueCallback2(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                Log.e("","这里没有返回值，我回调到我就行了");
                return null;
            }
        }, new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer) {
                Log.e("","这里有返回值，回调到这里，我还需要处理运算，我的上级才能收到我处理的值");
                return integer+10;//传递过来了一个值integer=1 再返回总结果值integer+10=1+10=11
            }
        });

    }
}
