package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-18 下午 7:56
 * Describe ：内部类  和  嵌套类
 *   内部类的特点：
 *   内部的类 能访问 外部的类  .外部的类 能访问 内部的类
 *  默认情况下：内部的类 不能访问 外部的类，要增加修饰符inner 成为内部类才可以访问外部类的成员
 *
 *
 *  嵌套类:类里面嵌套一个类
 *  嵌套类特点：外部的类 能访问 内部的嵌套类
 *  内部的类(无inner关键字) 不能访问 外部类的成员
 *
 *
 *
 *
 */
class InnerActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(Body("isOK").bodyInfo) // isOK

        // 内部类：
        Body("isOK").Heart().run() // 心脏访问身体信息:isOK
        Body("isOK").Kidney().work() // 肾脏访问身体信息:isOK
        Body("isOK").Hand().LeftHand().run() // 左手访问身体信息:isOK
        Body("isOK").Hand().RightHand().run() // 右手访问身体信息:isOK


        // 嵌套类：
        Outer().show() //  嵌套类
        Outer.Nested().output() // 嵌套类

    }
}


/**
 *内部类的特点： 内部的类 能访问 外部的类
 *              外部的类 能访问 内部的类
 *  默认情况下：内部的类 不能访问 外部的类，要增加修饰符inner 成为内部类才可以访问外部类的成员
 * */


class Body(_bodyInfo: String) { // 身体类

    val bodyInfo = _bodyInfo



    // 默认情况下：内部的类 不能访问 外部的类，要增加修饰符inner 成为内部类才可以访问外部类的成员


    inner class Heart { // 心脏类
        fun run() = println("心脏访问身体信息:$bodyInfo") // 心脏访问身体信息:isOK
    }

    inner class Kidney { // 肾脏
        fun work() = println("肾脏访问身体信息:$bodyInfo")
    }


    inner class Hand { // 手
        inner class LeftHand { // 左手
            fun run() = println("左手访问身体信息:$bodyInfo")
        }

        inner class RightHand { // 右手
            fun run() = println("右手访问身体信息:$bodyInfo")
        }


    }





}




/**
 *  嵌套类:类里面嵌套一个类
 *  嵌套类特点：外部的类 能访问 内部的嵌套类
 *  内部的类(无inner关键字) 不能访问 外部类的成员
 *
 * */

class Outer {

    val infoo: String = "OK"
    fun show() {
        Nested().output()
    }

    class Nested {
        // fun output() = println("$infoo")
        fun output() = println("嵌套类")

    }

}








