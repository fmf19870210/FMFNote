package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.File

/**
 * Create by Fangmingfei on 2023-03-17 下午 5:05
 * Describe ： open 关键字   as  is的使用
 */
class OpenActivity  : AppCompatActivity() {
    val TAG = "MainActivity"


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val person: Person = Student("张三")
        //输出子类重写的这个方法myPrintln()
         person.myPrintln() // 子类 的姓名是【张三】

        Log.e( "MainActivity", (person is Person).toString()) // true
        Log.e( "MainActivity", (person is Student).toString()) // true
        Log.e( "MainActivity", (person is File).toString()) // false

        // is + as = 一般是配合一起使用
       if(person is Student){
           (person as Student).myPrintln()  //子类 的姓名是【张三】
       }


        if (person is Person) {
             (person as Person).myPrintln() //子类 的姓名是【张三】  // 因为子类重写了父类
           var  result =  (person as Person).showName()
            Log.e( "MainActivity",result) // 父类 的姓名是【张三】
        }


        person.personMethod() // 父类独有的函数

        // 智能类型转换
        (person as Student).studentMethod() // 子类独有的函数
        // 智能类型转换：person会根据上面 as 转成的类型，自动明白，你现在的类型就是上面的Student类型
         person.studentMethod() // 子类独有的函数
         person.studentMethod( ) // 子类独有的函数
         person.personMethod()  //  父类独有的函数
    }


}

/**
 * KT所有的类，默认是final修饰的，不能被继承，和Java相反
  open：移除final修饰 类就可以继承了
  KT所有的函数，默认是final修饰的，不能被重写，和Java相反
  open：移除final修饰 函数就可以被重写了
 *
 * */

// KT所有的类，默认是final修饰的，不能被继承，和Java相反
// open：移除final修饰 类就可以继承了
open class Person(private val name: String){
      fun showName() = "父类 的姓名是【$name】"
    // KT所有的函数，默认是final修饰的，不能被重写，和Java相反
    // open：移除final修饰 函数就可以被重写了
    open fun  myPrintln()=   Log.e( "MainActivity",showName())

    // 父类独有的函数
    fun personMethod() =   Log.e( "MainActivity","父类独有的函数")

}


class Student(private val subName:String):Person(subName){
           fun showName2() = "子类 的姓名是【${subName}】"

         override fun  myPrintln()  =   Log.e( "MainActivity",showName2())

    // 子类独有的函数

    fun studentMethod() =  Log.e( "MainActivity","子类独有的函数")

}







