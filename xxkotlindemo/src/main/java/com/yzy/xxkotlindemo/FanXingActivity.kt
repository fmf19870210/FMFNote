package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-23 下午 5:24
 * Describe ： 泛型函数学习
 */
class FanXingActivity : AppCompatActivity()  {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val stu1 = Student2("张三", 88, '男')
        val stu2 = Student2("李四", 78, '女')

        val tea1 = Teacher("王五", 77, '男')
        val tea2 = Teacher("赵六", 89, '女')
        //2. 四个对象打印
         println(KtBase104(true, stu1).getObj()) //Student2(name=张三, age=88, sex=男)
        println(KtBase104(true, stu2).getObj()) // Student2(name=李四, age=78, sex=女)
        println(KtBase104(true, tea1).getObj())  // Teacher(name=王五, age=77, sex=男)
        println(KtBase104(true, tea2).getObj())  // Teacher(name=赵六, age=89, sex=女)
        println(KtBase104(false, tea2).getObj()) //  null
        println(KtBase104(false, tea2).getObj()?:"万能对象不存在为null")  // 万能对象不存在为null


           //3. 对象打印 + run + ?:
         val r:Any =  KtBase104(true,stu1).getObj()?.run {
             // 如果 getObj 返回有值，就会进来
             // this == getObj万能对象本身
               println("输出万能对象是:$this") // 返回Unit
             545.4f // 返回Float
           }?:"当前万能对象存在,为null"

      println(r) //  545.4


        // 4.对象打印 + apply + ?:
        //apply特点：永远都是返回 getObj.apply  getObj本身
       val r3:Teacher? =  KtBase104(true,tea1).getObj().apply {
             // this == getObj本身
          if(this!=null){
             println("输出当前对象this = $this") //输出当前对象this = Teacher(name=王五, age=77, sex=男)

         }else{
              println("当前对象this不存在 , this=$this") //  当前对象this不存在 , this=null
         }
       }

        println("r3:$r3") // r3:Teacher(name=王五, age=77, sex=男)


        /**
         *
         * 5.show(t: T) + apply + ?:
         *
         * */
        show2("Derry") //当前对象it是=Derry
        show2("Kevin") //  当前对象it是=Kevin
        show2("OK")  //  当前对象it是=OK
        show2(null)  //   当前对象不存在为null=null


    }
}




/**
 * 1.万能对象返回器 : 运用 takeIf，如果Boolean=true 返回万能对象本身否则返回null
 * */
class KtBase104<T>(private val isTrue:Boolean,private val obj:T){
    fun getObj():T?=obj.takeIf { isTrue }
}


data class Student2(val name: String , val age: Int, val sex: Char)
data class Teacher(val name: String , val age: Int, val sex: Char)

















