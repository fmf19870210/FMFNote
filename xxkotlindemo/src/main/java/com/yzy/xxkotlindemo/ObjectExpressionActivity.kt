package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-18 下午 4:41
 * Describe ： 对象表达式学习   KtBase88
 *   1.匿名对象 表达式方式的学习
 *  2.具名实现方式
 *  3. 对Java的接口    用匿名对象表达式方式  KT[object : 对象表达式]  方式一
 *  4. 对Java的接口 用   Java最简洁的方式(lambda) 方式二
 *  5. 对KT的接口    用匿名对象表达式方式   KT[object : 对象表达式]  方式一
 *  6.  对KT的接口 用   Java最简洁的方式(lambda) 方式二   不行行不通  //todo
 */
class ObjectExpressionActivity  : AppCompatActivity()  {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 1.匿名对象 表达式方式的学习
         val p:KtBase88  =  object:KtBase88(){
             override fun add(info: String) {
                 super.add(info)
                 println("我是匿名对象 add:$info")
             }

             override fun del(info: String) {
                 super.del(info)
                 println("我是匿名对象 del:$info")
             }
         }

        p.add("李元霸")
        p.del("李连杰")


        // 2.具名实现方式
        val p2 = KtBase88Impl()
         p2.add("刘一")
         p2.del("六二")



        // 对Java的接口    用匿名对象表达式方式  KT[object : 对象表达式]  方式一
        val p3:Runnable=object:Runnable{
            override fun run() {
                println("Runnable run ...")
            }
        }

      p3.run() //  Runnable run ...


        // 对Java的接口 用   Java最简洁的方式(lambda) 方式二

        val  p4:Runnable = Runnable {
            println("Runnable run2 ...")
        }
        p4.run()  //  Runnable run2 ...


        // 对KT的接口    用匿名对象表达式方式   KT[object : 对象表达式]  方式一
        val  p5 =   object:RunnableKT{
            override fun run() {
                println("KT的接口RunnableKT 实现方式一 run ...")
            }
        }
        p5.run() // KT的接口RunnableKT 实现方式一 run ...

        // 对KT的接口 用   Java最简洁的方式(lambda) 方式二   不行行不通

        /*  val  p6 = RunnableKT {
            }
       p6.run()
       */


    }
}


// 具名实现  具体名字 == KtBase88Impl
class KtBase88Impl:KtBase88() {
    override fun add(info: String) {
        super.add(info)
        println("我是具名对象 add:$info")
    }

    override fun del(info: String) {
        super.del(info)
        println("我是具名对象 del:$info")
    }

}


// KT的接口

interface RunnableKT {
    fun run()
}
