package com.yzy.xxkotlindemo.mapflatmapfilterzip

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-03-30 上午 11:42
 * Describe ： map的学习
 */
class MapActivity : AppCompatActivity()  {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /**
        * inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
        传入参数 T T T  --->  新的集合(R, R, R)
       原理：就是把你 匿名函数 最后一行的返回值元素  加入一个新的集合，新集合的泛型是R，并且返回新集合
        */
       val list:List<String> = listOf("李元霸", "李连杰", "李小龙")
        println(list)
        val list2 = list.map(transform = {
            println("输出当前it=$it") // 输出当前it=李元霸  输出当前it=李连杰  输出当前it=李小龙
                it //最后一行返回值元素
        })
        println(list2) // [李元霸, 李连杰, 李小龙]


        val list3 = list.map {
            "姓名是:$it" ////最后一行返回值元素
        }.map {
            "$it，文字的长度是:${it.length}" //最后一行返回值元素
        }.map {
               true
               11.11
               it.length//最后一行返回值元素
            }

        println(list3) //  [16, 16, 16]








    }

}