package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-18 下午 3:41
 * Describe ： Any() ；所有的类默认都集成了Any()
 *   object  关键字 : 添加object 关键字的类 既是单例实例,又是类名,只会初始化创建一次
 *
 *    KtBase87
 */
class ObjectActivity  : AppCompatActivity()  {
       val TAG = "MainActivity"


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // object KtBase87 既是单例的实例，也是类名  只会创建一次
        Log.e( "MainActivity", KtBase87.toString()) // 背后代码：println(KtBase87.INSTANCE)
        Log.e( "MainActivity", KtBase87.toString()) // 背后代码：println(KtBase87.INSTANCE)
        Log.e( "MainActivity", KtBase87.toString())
        Log.e( "MainActivity", KtBase87.toString())

        // 背后代码：KtBase87.INSTANCE.show();
        Log.e( "MainActivity", KtBase87.show().toString())
    }
}
















