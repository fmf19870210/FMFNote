package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解;

import android.os.Bundle;

import com.yzy.xxkotlindemo.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Create by Fangmingfei on 2023-04-01 下午 9:53
 * Describe  ： java 类 调用kt类中被注解的函数
 */
public class JavaActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // java类中 调用kt类中的非伴生对象的字段info1  和 函数showAction1()
        // 需要创建KtBase89CompanionObject()对象开可以调用非伴生对象的字段和函数
        KtBase89CompanionObject ktBase89CompanionObject  =    new KtBase89CompanionObject();
        ktBase89CompanionObject.getInfo1();
        ktBase89CompanionObject.showAction1();


       // java类中调用kt类中伴生对象中的非注解的字段和函数 需要通过KtBase89CompanionObject.Companion才能调用
        KtBase89CompanionObject.Companion.getInfo2(); //调用kt类KtBase89CompanionObject中的info2字段
        KtBase89CompanionObject.Companion.showAction2(); //调用kt类KtBase89CompanionObject中的showAction22()函数


        // java类中调用kt类中被伴生对象中的被注解的字段和函数  直接调用
        String info2 = KtBase89CompanionObject.info3; // 调用kt类KtBase89CompanionObject中被@JvmField注解过的字段info2
        KtBase89CompanionObject.showAction3();  // 调用kt类KtBase89CompanionObject中被@JvmStatic注解过的函数showAction2()

        //这个比较特殊
        KtBase89CompanionObject.Companion.toast("方明飞");

    }
}
