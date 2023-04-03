package com.yzy.xxkotlindemo.扩展函数扩展属性扩展文件

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R
import com.yzy.xxkotlindemo.myApply
import com.yzy.xxkotlindemo.myLet

// 导入扩展文件UtilsExtend
// 在工作中非常有用，可以把很多的扩展操作，写到某一个地方，到时候引入过来用，比较独立化
import com.yzy.xxkotlindemo.randomItemValuePrintln

/**
 * Create by Fangmingfei on 2023-03-27 下午 2:52
 * Describe ：  给某个类增加扩展函数的学习
 *    1. 扩展xxx函数里面会持有this ==当前类对象本身
 *
 *     超类上定义扩展函数
 *   1.  给超类Any添加一个扩展函数 那么所有的类都能调用超类Any的这个扩展函数
      2. KT内置的扩展函数，被我们重复定义，属于覆盖，而且优先使用我们自己定义的扩展函数

      对泛型类T 进行函数的扩展
    1. 所有的具体类型或者具体函数方法返回的类型都是泛型,那么所有的具体类型都可以调用该泛型的扩展函数


     标准函数与泛型扩展函数

     对某个类的属性扩展
        val String.spreadField:String
        get() = "Derry"

     可空类型扩展函数
     var infoValue : String ? = null
     infoValue.outputStringValueFunGet("helloWorld")

扩展文件 UtilsExtend.kt


 */
class AddSpreadFunctionActivity  : AppCompatActivity(){




    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val p = KtBase113("张三", 28, '男')
        p.show() // KtBase113增加扩展show函数, name:张三, age:28, sex:男
        println(p.getInfo()) // KtBase113增加扩展getInfo函数, name:张三, age:28, sex:男


        println("Derry".addExtAction(8))
        println("Kevin".addExtAction(3))

        "这个是我的日志信息".showStr() // 这个是我的日志信息
        "Beyond".showStr() // Beyond


        ResponseResult1("login success", 200).showAnySpreadFunction()
        ResponseResult4("login success", 200).showAnySpreadFunction()

        "Derry1".showAnySpreadFunction()
        999.showAnySpreadFunction()
        544354.5f.showAnySpreadFunction()
        '男'.showAnySpreadFunction()

         '女'.showAnySpreadFunction2().showAnySpreadFunction2().showAnySpreadFunction2()

        println(123.showFanXingSpreadFunction()) // 当前泛型为是Int类型
        println(showFanXingSpreadFunction()) // 未知类型
        println(Unit.showFanXingSpreadFunction()) // 当前泛型为无参返回函数类型
        println(test().showFanXingSpreadFunction()) //当前泛型为无参返回函数类型
        println(testB().showFanXingSpreadFunction()) // 当前泛型为Boolean类型


        test().let {  }
        val r: Char = "Derry".myLet {
            it
            true
            "OK"
            'A'
        }

        123.myLet {
            it
        }

        'C'.myLet {
            it
        }


         "hello world".apply(block={
             println("输出内置函数apply的当前this=$this")
         })

        "hello world2".myApply(lambda = {
            println("输出自定义的内置函数myApply当前this=$this")
            this.length
        })




  //todo==================对String 进行属性扩展========================================================================================
        println("".spreadField) //Derry
        println("hhh".stringAllInfoValueVal) /// 当前1679985925744这个时间点被调用了一次，当前的对象:hhh，当前字符串长度是:3


     //todo======================可空类型扩展函数=====================================================================
        var infoValue : String ? = null
        println(infoValue.outputStringValueFunGet("helloWorld")) // 返回传入的默认值:helloWorld
        infoValue=""
        println(infoValue.outputStringValueFunGet("helloWorld")) // 返回当前对象值:
        infoValue = "Derry"
        println(infoValue.outputStringValueFunGet("helloWorld")) // 返回当前对象值:Derry




    // todo===============================扩展文件=======================================================================
        val list : List<String> = listOf("李元霸", "李连杰", "李小龙")
        val set : Set<Double> = setOf(545.5, 434.5, 656.6)
        // 如果不使用 扩展文件
        //取随机数
        println(list.shuffled().first()) //李小龙
        println(set.shuffled().first()) // 434.5

        // 使用 扩展文件
        list.randomItemValuePrintln() // 当前扩展对象T=[李元霸, 李连杰, 李小龙]   //李元霸
        set.randomItemValuePrintln() // 当前扩展对象T=[545.5, 434.5, 656.6]   // 434.5

    }






}



// 假设这个代码是，开源的，或者是很庞大JDK源码，或者是非常复杂的开源库
class KtBase113 (val name: String, val age: Int, val sex: Char)


// 给类KtBase113增加扩展函数show()
fun KtBase113.show() {
// this 就是本类对象KtBase113
    println("KtBase113增加扩展show函数, name:${this.name}, age:${this.age}, sex:${this.sex}")
}


// 给类KtBase113增加扩展函数getInfo()
fun KtBase113.getInfo():String = "KtBase113增加扩展getInfo函数, name:${this.name}, age:${this.age}, sex:${this.sex}"


//对String类进行扩展函数addExtAction
fun String.addExtAction(number: Int) =  this + "@".repeat(number)
//对String类进行扩展函数showStr
fun String.showStr() = println(this)



// 给超类Any添加一个扩展函数 那么所有的类都能调用超类Any的这个扩展函数
fun Any.showAnySpreadFunction() = println("打印当前超类对象Any=$this")


//此扩展函数返回当前类对象
fun Any.showAnySpreadFunction2() : Any {
    println("返回当前超类对象Any=$this")
    return this
}

data class ResponseResult1(val msg: String, val code: Int)
data class ResponseResult2(val msg: String, val code: Int)
data class ResponseResult3(val msg: String, val code: Int)
data class ResponseResult4(val msg: String, val code: Int)
fun test() {}
fun testB():Boolean {return  true}

//定义泛型T的扩展函数
fun <T> T.showFanXingSpreadFunction()=
      when(this){
         is String ->"当前泛型为String类型"
         is Int -> "当前泛型为是Int类型"
         is Char -> "当前泛型为是Char类型"
         is Float -> "当前泛型为Float类型"
         is Double -> "当前泛型为Double类型"
         is Boolean -> "当前泛型为Boolean类型"
         is Unit -> "当前泛型为无参返回函数类型"
         else -> "未知类型"
     }







 /**
  * 对String 进行属性扩展
  * */

val String.spreadField:String
  get() = "Derry"

val String.stringAllInfoValueVal
get() = "当前${System.currentTimeMillis()}这个时间点被调用了一次，当前的对象:$this，当前字符串长度是:${this.length}"




/**
 * 可空类型扩展函数
 * */
fun String?.outputStringValueFunGet(defaultValue : String):String{
    return if(this==null){
        "当前对象值this=${this},没办法就返回传入的默认值:$defaultValue"
    }else{
     "返回当前对象值this=$this"
    }
}










