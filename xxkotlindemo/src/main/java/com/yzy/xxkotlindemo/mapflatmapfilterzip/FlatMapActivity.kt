package com.yzy.xxkotlindemo.mapflatmapfilterzip

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-03-30 上午 11:42
 * Describe ： flatMap  的学习
 */
class FlatMapActivity : AppCompatActivity()  {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

             /**Kotlin语言的变换函数-flatMap
              map {返回类型：返回值每一个元素T是个类型 String Int Boolean Char ...  是把每一个元素（String）加入到新集合，最后返回新集合 List<String>}
              flatMap {返回类型：返回值每一个元素T是个集合:集合1 集合2 集合3 ... 是把每一个元素（集合）加入到新集合，最后返回新集合 List<List<String>>
                但是会对List<List<String>> 内部进行简化处理 最终返回List<String>  }
             // TODO flatMap 相当于 List<List<String>> 集合的集合，有嵌套关系 现在进行了简化 返回 List<String>  把小集合元素都放到大集合里去了
             // TODO flatMap源码原理就是:就是把你 匿名函数的 最后一行代码的返回值(就是一个集合listOf(......)) 加入一个新的大集合，新集合的泛型是R，并且返回新集合
              * */

             val list : List<String> = listOf("李四", "王五五", "赵六六六")
        //  todo  val newList正常是List<List<String>>类型  这里进行了简化为 List<String>类型  把小集合元素都放到大集合里去了
        val newList:List<String> = list.map {
            "你的姓名是:it=$it" //最后一行代码  每次返回一个 String : 你的姓名是:it=李四  你的姓名是:it=王五五 你的姓名是:it=赵六六六
        } //  [你的姓名是:it=李四, 你的姓名是:it=王五五, 你的姓名是:it=赵六六六]
            .map { itit->
               "$itit,你的文字长度是:${itit.length}"  //最后一行代码  每次返回一个 String : 你的姓名是:it=李四,你的文字长度是:11 你的姓名是:it=王五五,你的文字长度是:12  你的姓名是:it=赵六六六,你的文字长度是:13
            }     //[你的姓名是:it=李四,你的文字长度是:11, 你的姓名是:it=王五五,你的文字长度是:12, 你的姓名是:it=赵六六六,你的文字长度是:13]
            .flatMap {ititit->
                 listOf("$ititit 在学习C++","$ititit 在学习Java","$ititit 在学习Kotlin") // 每次返回一个小集合
            }
          println(newList)
        /*
        [
        你的姓名是:it=李四,你的文字长度是:11 在学习C++,你的姓名是:it=李四,你的文字长度是:11 在学习Java,你的姓名是:it=李四,你的文字长度是:11 在学习Kotlin,

        你的姓名是:it=王五五,你的文字长度是:12 在学习C++,你的姓名是:it=王五五,你的文字长度是:12 在学习Java,你的姓名是:it=王五五,你的文字长度是:12 在学习Kotlin,

         你的姓名是:it=赵六六六,你的文字长度是:13 在学习C++,你的姓名是:it=赵六六六,你的文字长度是:13 在学习Java,你的姓名是:it=赵六六六,你的文字长度是:13 在学习Kotlin

         ]

        * */


    }

}