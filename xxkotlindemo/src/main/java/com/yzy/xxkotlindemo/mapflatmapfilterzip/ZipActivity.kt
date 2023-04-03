package com.yzy.xxkotlindemo.mapflatmapfilterzip

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-04-01 下午 5:51
 * Describe ：kotlin的合并函数-zip学习
 *
 *原理：就是把 第一个集合 和 第二个集合 合并起来，创建新的集合，并返回
 *      返回的新的集合(元素Pair(K, V)，元素Pair(K, V)，元素Pair(K, V))
 *       元素Pair(K, V)  K就是第一个集合的元素   V就是第二个集合的元素
 *     如果用Java实现两个集合的合并很复杂
 *
 * public infix fun <T, R> Iterable<T>.zip(other: Iterable<R>): List<Pair<T, R>> {
return zip(other) { t1, t2 -> t1 to t2 }
}
 *
 *
 *
 */
class ZipActivity : AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val names = listOf("张三", "李四", "王五")
        val ages = listOf(20, 21, 22)

        val zip :List<Pair<String, Int>> = names.zip(ages)
        println(zip) //[(张三, 20), (李四, 21), (王五, 22)]
        println(zip.toMap()) //  {张三=20, 李四=21, 王五=22}
        println("===============================")
        println(zip.toMutableList()) //  [(张三, 20), (李四, 21), (王五, 22)]
        println("===============================")
        println(zip.toMutableSet()) //  [(张三, 20), (李四, 21), (王五, 22)]

        println("===============================")
         zip.forEach {
             // it == Pair<String, Int>
             println("姓名是:${it.first}, 年龄是:${it.second}")
             println(it)
         }

        println("===============================")
             // map 普通方式
        zip.toMap().forEach { k:String, v:Int ->
            println("姓名是:${k}, 年龄是:${v}")
        }


        println("===============================")
        // map 解构的方式
        zip.toMap().forEach { (k:String, v:Int) ->
            println("姓名是:${k}, 年龄是:${v}")
        }


        println("===============================")
        zip.toMap().forEach {
            // it == Map的元素 每一个元素 有K和V，  Map.Entry<String, Int>
            // it == Pair<String, Int>
            println("姓名是:${it.key}, 年龄是:${it.value}")
        }


    }













































}