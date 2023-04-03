package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-29 下午 2:40
 * Describe ：
 自定义的中缀表达式 + 扩展函数 一起用的     使用者： "一".gogogo(1)  "一" gogogo 1
 1.条件一  对第一个参数 C1.gogogo()  进行函数扩展
 2.条件二  需要在 扩展函数的括号(c2: C2)里面，传递一个参数


 *
 *
 */
class InfixActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // 下面是我们map自带的中缀表达式
        // public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
        mapOf("零".to(0))

        mapOf("一" to 1)
        mapOf("二" to 2)
        mapOf("三" to 3)

        // 下面是我们自己写的中缀表达式
        123.gogogo('男')
        "Derry".gogogo('M')
        "Derry2" gogogo 'M'
    }

}



// 自定义的中缀表达式 + 扩展函数 一起用的     使用者： "一".gogogo(1)  "一" gogogo 1
// 1.条件一  对第一个参数 C1.gogogo()  进行函数扩展
// 2.条件二  需要在 扩展函数的括号(c2: C2)里面，传递一个参数
private infix fun <C1,C2> C1.gogogo(c2:C2){
    println("我们的中缀表达式，对一个参数对象C1=$this")
    println("我们的中缀表达式，对二个参数的内容是:$c2")
}










