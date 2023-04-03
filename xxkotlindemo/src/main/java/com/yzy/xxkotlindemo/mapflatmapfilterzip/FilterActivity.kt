package com.yzy.xxkotlindemo.mapflatmapfilterzip

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-03-30 下午 4:17
 * Describe ： 过滤函数-filter
     filter原理：filter {true/false}  true他会加入到新的集合 进行组装新集合 返回，  否则false，过滤掉，不加入，返回空集合

 *
 *
 */
class FilterActivity  : AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameLists = listOf(
            listOf("黄晓明", "李连杰", "李小龙"),
            listOf("刘军", "李元霸", "刘明"),
            listOf("刘俊", "黄家驹", "黄飞鸿")
        )

         nameLists.map {
            // it ==  nameLists的元素 == listOf("黄晓明", "李连杰", "李小龙"),
            println(it)
        }

        nameLists.flatMap {
            // it ==  nameLists的元素 == listOf("黄晓明", "李连杰", "李小龙"),
            println(it)
            listOf("")
        }


          nameLists.flatMap {outit ->
              println("过滤前输出outit:$outit") // 过滤前输出outit:[黄晓明, 李连杰, 李小龙]  ...
               outit.filter {innerit->
                  //println("输出符合过滤元素:$innerit") // 进来了 9次  输出符合过滤元素:黄晓明   ...
                   true
                 // false
                   // todo filter原理：filter {true/false}  true他会加入到新的集合 进行组装新集合 返回，  否则false，过滤掉，不加入，返回空集合
               }

            // println("过滤后输出outit:$outit")
          } // 当前返回符合过滤条件的集合 List<String>
              .map { accordFilterit ->
                  println("输出符合过滤条件的元素:$accordFilterit") // 输出符合过滤条件的元素:黄晓明  .....
              }


            nameLists.map {
                it.filter {
                    true
                  // false
                }
            }.map {
                 if(it.isNotEmpty()){
                     println("输出符合过滤条件的集合::$it") // 输出符合过滤条件的集合::[黄晓明, 李连杰, 李小龙]  ...
                 }else{
                     println("没有符合过滤条件的集合为空") // 没有符合过滤条件的集合为空
                 }
            }



           nameLists.flatMap {
                 it.filter {
                     true
                 }
           }.map {
               if (it.isNotEmpty()) {
                   println("输出符合过滤条件的元素:::$it") // 输出符合过滤条件的元素:::黄晓明  输出符合过滤条件的元素:::李连杰 输出符合过滤条件的元素:::李小龙   ...
               } else {
                   println("没有符合过滤条件的元素为空") // 没有符合过滤条件的集合为空

               }

           }

        // todo 过滤器返回的List<T> 返回给 map 后的效果： [黄晓明, 李连杰, 李小龙]   [刘军, 李元霸, 刘明]   [刘俊, 黄家驹, 黄飞鸿]
        // todo 过滤器返回的List<T> 返回给 flatMap 效果: 黄晓明   李连杰   李小龙   刘军   李元霸   刘明   刘俊   黄家驹   黄飞鸿


       nameLists.flatMap { it ->
              it.filter {
                it.contains('李')  // 包含 ‘李’ true，否则是false
            }
       }.map {
           println("输出符合过滤条件的元素4: $it") // 输出符合过滤条件的元素4: 李连杰  输出符合过滤条件的元素4: 李小龙   输出符合过滤条件的元素4: 李元霸

       }


    }


}












































