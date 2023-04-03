package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-24 下午 9:20
 * Describe ： 泛型类型约束学习
 */
class FanXingClassActivity  : AppCompatActivity()   {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 祖宗类 顶级父类
        val any =  MyAnyClass("Derry1")
        println(any) // com.yzy.xxkotlindemo.MyAnyClass@d7bbb09
        val per = PersonClass("Derry1") // 父类
        println(per) //  com.yzy.xxkotlindemo.PersonClass@2a1910e

        val stu = StudentClass("Derry1") // 子类
        println(stu)   // com.yzy.xxkotlindemo.StudentClass@7d1372f

        val pea = TeacherClass("Derry1") // 子类
        println(pea) // com.yzy.xxkotlindemo.TeacherClass@3f013c


        val dog = DogClass("Derry1") // 其他类 另类
        println(dog) // com.yzy.xxkotlindemo.DogClass@d9bfec5


        //  KtBase106(MyAnyClass("方明飞")).getObj()   // 报错了，类型限定了

      val r2 =  KtBase106(inputTypeValue=PersonClass("人类"),isTrue=true).getObj()
        println(r2) // com.yzy.xxkotlindemo.PersonClass@469b01a

        val r3 =  KtBase106(inputTypeValue=StudentClass("方明飞学生"),isTrue=true).getObj()
        println(r3) // com.yzy.xxkotlindemo.StudentClass@2c4174b

        //   KtBase106(inputTypeValue=CatClass("小白"),isTrue=true).getObj() // 报错了，类型限定了

    }
}



open class MyAnyClass(name: String) // 祖宗类 顶级父类


open class PersonClass(name:String):MyAnyClass(name = name) // 父类
class StudentClass(name: String):PersonClass(name = name)  // 子类
class TeacherClass(name: String) : PersonClass(name = name) // 子类



class DogClass(name: String) // 其他类 另类
class CatClass(name: String) // 其他类 另类



// T : PersonClass   相当于  Java的 T extends PersonClass
// T:PersonClass本身 与 PersonClass的所有子类(StudentClass TeacherClass) 都可以使用， 其他的类，都不能兼容此泛型
class KtBase106<T:PersonClass> (private val inputTypeValue:T,private val isTrue:Boolean =  true){
    fun getObj():T? = inputTypeValue.takeIf { isTrue }
}


















