package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-24 下午 2:43
 * Describe ：泛型转换
 */
class FanXingTransformActivity  : AppCompatActivity()  {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 2.map() int -> str 最终接收是什么类型   返回String类型
       val transFormedResult   =    KtBase105(isMap = true,inputType = 5434)
                                    .map(mapAction = {
                                        it  // lambda最后一行是 返回值
                                        it.plus(1) // lambda最后一行是 返回值
                                        it.toString() // lambda最后一行是 返回值
                                        "我的it是:$it" // lambda最后一行是 返回值
                                    })
        println(transFormedResult is String) //true
        println(transFormedResult is String?) //true
        println(transFormedResult?:"transFormedResult不存在,为=$transFormedResult") // 我的it是:5434
        println("输出最总的结果 $transFormedResult") //  输出最总的结果 我的it是:5434


        // 2.map() int -> str 最终接收是什么类型  返回null
        val transFormedResult2  =    KtBase105(isMap = false,inputType = 5434)
            .map(mapAction = {
                it  // lambda最后一行是 返回值
                it.plus(1) // lambda最后一行是 返回值
                it.toString() // lambda最后一行是 返回值
                "我的it是:$it" // lambda最后一行是 返回值
            })
        println(transFormedResult2 is String) //false
        println(transFormedResult2 is String?) //true
        println(transFormedResult2 ?:"transFormedResult不存在,为=$transFormedResult2") // transFormedResult不存在,为=null
        println("输出最总的结果 $transFormedResult2")     //输出最总的结果 null

        // 3.map per -> stu 最终接收是什么类型 Persons类型没有进行类型转换
        val   transFormedResult3 = KtBase105(isMap = true,inputType = Persons("李四",99))
            .map(mapAction = {
                // it == Persons对象 == inputType
                 it
                Students(it.name,it.age)
            })

        println("输出最终的数据结果:=$transFormedResult3") // 输出最终的数据结果:=Persons(name=李四, age=99)
                                                          //  输出最终的数据结果:=Students(name=李四, age=99)

        // map函数 模仿RxJava变换操作
        val  r3 = map( inputValue = 123,isMap = true,mapActionLambda = {
                 it.toString()
            "map包裹[$it]" // lambda表达式最后一行，就是返回值
                })
         println(r3)
    }

}



class  KtBase105<T>(val isMap:Boolean = false,val inputType:T){
    fun <R> map(mapAction:(T)->R?): R? {
         return mapAction.invoke(inputType).takeIf { isMap }
     }
}






data class Persons(val name: String, val age: Int)
data class Students(val name: String, val age: Int)