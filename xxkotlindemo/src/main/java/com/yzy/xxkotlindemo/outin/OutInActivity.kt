package com.yzy.xxkotlindemo.outin

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R

/**
 * Create by Fangmingfei on 2023-03-25 下午 5:49
 * Describe ：
 * 1.out  协变    int 逆变  的学习
 *   凡是添加了out协变 的泛型或者类 只能被读取 不能被修改
 *   凡是添加了in逆变的泛型或者类  只能被修改更新   不能被读取
 *   out ： KtBase109.java   KtBase109.kt
 *   int  ：  KtBase110.java  KtBase110.kt
 *
 最终总结:
 协变：out 泛型处的父类 = 具体的子类 (子类赋值给父类  合情合理  协和顺变)
 逆变：in  泛型处的子类 = 具体的父类 (父类赋值给子类 大逆不道逆变)

 * 2.什么时候使用 协变：out ? 什么时候使用 逆变：in？
 *     如果一个类只能修改 ，不能让外界读取时， 这个类的泛型声明为 逆变 in T   class SetClass<in T>()
 *      如果一个类只能读取, 不能修改, 那么这个类的泛型声明为协变 out T        GetClass<out T>(_item: T)
 *
 *
 */
class OutInActivity: AppCompatActivity(){


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      // 逆变 in T  SetClass 只能修改 更改 不能给外界读取
        val p1 = SetClass<String>()
        p1.set1("Derry")
        p1.set2("Kevin")
        println(p1) // com.yzy.xxkotlindemo.outin.SetClass@de06ee9

       // 协变 out T GetClass 只能读取，不能修改 更改
        val p2 = GetClass("李四")
        println(p2.get1())// 李四
        val p3 = GetClass("王五")
        println(p3.get3())// 王五

    }



}




// in T  out T 声明处指定关系  声明处泛型  这个是Java没有的功能

// 整个 SetClass 里面的所有成员 泛型相关，只能修改 更改，
//                                    不能获取人家 读取人家的
// 小结：当我们 对这个整个类里面的泛型定义为 in T ，只能修改 ，不能让外界读取时，可以声明 in T 逆变泛型
class SetClass<in T>() {

    // 200个函数 这200个函数 对T只能修改，不能给外界读取
    // ...

    fun set1(item: T) {
        println("set1 你要设置的item是:$item")
    }

    fun set2(item: T) {
        println("set2 你要设置的item是:$item")
    }

    fun set3(item: T) {
        println("set3 你要设置的item是:$item")
    }

    // ...

    // 不能给外界读取 (增加in T 泛型后，不能给外界读取，所以编译不通过)
    /*fun get1() : T? {
        return null
    }

    fun get2() : T? {
        return null
    }

    fun get3() : T? {
        return null
    }*/

    // ...
}






// 整个 GetClass 里面的所有成员 泛型相关，不能修改 更改，
//                                    只能获取人家 读取人家的
// 小结：当我们 对这个整个类里面的泛型out T，只能给读取 ，不能修改 更改，可以声明 out T 协变泛型
class GetClass<out T>(_item: T) {

    val item: T = _item

    // 200个函数 这200个函数 对T只能读取，不能给外界修改 更改
    // ...

    // 不能给外界修改 更改 (增加out后，不能给外界修改 更改，所以编译不通过)
    /*fun set1(item : T) {
        println("set1 你要设置的item是:$item")
    }

    fun set2(item : T) {
        println("set2 你要设置的item是:$item")
    }

    fun set3(item : T) {
        println("set3 你要设置的item是:$item")
    }*/

    // ...


    fun get1(): T {
        return item
    }

    fun get2(): T {
        return item
    }

    fun get3(): T? {
        return item
    }

    // ...
}



