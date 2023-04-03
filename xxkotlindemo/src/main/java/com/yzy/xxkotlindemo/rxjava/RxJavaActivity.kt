package com.yzy.xxkotlindemo.rxjava

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R
import com.yzy.xxkotlindemo.create
import com.yzy.xxkotlindemo.map
import com.yzy.xxkotlindemo.observer

/**
 * Create by Fangmingfei on 2023-04-03 下午 3:05
 * Describe ：自定义RX的学习 全部用KT的基础来写    RxJavaCoreClassObject UtilsExtend.kt
 *
 *   create 输入源，没有任何参数给你，  输出源：你是输出就行（所有类型，万能类型）
  map 输入源 就是create的输出源的valueItem，  输出源：你是输出就行（所有类型，万能类型）
  observer 输入源 就是 map 存储的 valueItem，  消费完成就行，全部结束
 *
 */
class RxJavaActivity  : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // create 输入源，没有任何参数给你，  输出源：你是输出就行（所有类型，万能类型） 最后一行数据类型作为返回值
        var create :RxJavaCoreClassObject<Float> = create(action = {
            // .... 省略 ，万能类型，几百种类型，其实都一样的
            "Derry"
            123
            true
            "AAAAAAAA"
            5435.54f // 最后一行  作为create高阶函数的 返回数据类型
        })

        println(  create.get()) // 5435.54


        var mapedValue:RxJavaCoreClassObject<String>  = create.map {
             //todo  注意 这里的this 就是  create :RxJavaCoreClassObject<Float>的最后一行返回数据  5435.54f
            println("输出当前this对象=$this") // 输出当前this对象=5435.54
            "你好"  // 最后一行  作为map()函数返回数据类型  RxJavaCoreClassObject<String>
         }
        println(mapedValue.get()) //   你好



        mapedValue.observer(observerAction={
            println("输出最终的this值=$this") // 输出最终的this值=你好
        }

        )




    }
}