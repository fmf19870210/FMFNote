package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-04-01 下午 8:48
 * Describe ：
 */
class SingletonDemoActivity   : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          SingletonDemo4.getInstance().show()
          SingletonDemo4Kt.instance.show()

    }
}