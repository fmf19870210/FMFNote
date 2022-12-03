package com.fmf.fmfnote.kotlin

import android.app.IntentService
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.fmf.fmfnote.R
import kotlin.coroutines.Continuation

class MainActivity : AppCompatActivity() {


    private var goodsId: String ?= null
    private var goodsId2: String=""
   // var time: Long?=null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     var handler =    Handler()
    //  time!!.toFloat()
   //   time?.toInt()



         //数字类型(int、long、float)不会自动转型，必须要进行明确的类型转换
         val double:Double=100.toDouble()
         val  long:Long=100.5F.toLong()

              yc(double,long)
            var intparam:Int=getIntMeth(double,long)


        var a: String ="abc"  // 变量a 不能为空
        val l = a.length   //调用 a 的方法 不会 NPE 异常
       //  a = null // 编译错误, 变量a 不能为空


        var b: String? = "abc"  //指定一个变量可null是通过在类型的最后增加一个问号？
     //       b = null
     //    val ll=b.length    //编译错误  当变量声明为可空时，在调用它的属性时无法通过编译
    //    val lll = b?.length  //使用安全操作符   ?.     可通过编译
    //     val  llll=b?.chars()
   //     var b: String? = "abc"


   //     val list= listOf(1, 3, 5, 7, 9)
    //    println(list.any { it > 13 })  //false
   //     println(list.any { it > 7 })   //true

        val list = listOf(1, 3, 5, 7, 9)
       //  println(list.max()) //最大 9

     //   println(list.maxBy { it }) //最大9

     //   println(list.maxBy { -it }) //最大-1





     //   println(list.min()) //最小 1
      //  println(list.minBy { it }) //最小1
     //   println(list.minBy { -it })   //最小-9

       // val name = "leavesC"
      //  println(name.last())
     //   println(name.toDouble())

        var name:String?="ycc"
        checks(name) //3

       // name=null
       //  checks(name) //KotlinNullPointerException

    // var result:Int=1;
    //   var result2= result as? Double
    //    println(result2)



        val list2 = listOf(1, 4, 10, 4, 10, 30)
        if(list2.size>0){
            println(list2.size)
        }else{
            println("list2 is empty")
        }


           // 正常的for循环,=java 的for 循环
        for (i in list2) {
            println(i)
        }


          //通过索引来遍历 集合
        val items = listOf("H", "e", "l", "l", "o")
        for (i  in items.indices) {
          println(items.indices)  //items.indices  0..4
          println(items[i])   //输出对应的角标i 的value 值 "H", "e", "l", "l", "o"

           //  println("角标 ${i } 对应的集合的值是 ${items[i]}")
            //角标 0 对应的集合的值是 H   ...  角标 4 对应的集合的值是 o

        }


        val strValue = "leavesC"
        parserType(strValue) //value is String , length : 7

        val longValue = 200L
        parserType(longValue)


        val tempValue ="leavesC" as String
        println(tempValue.length)  //7



 //       val tempValue2 ="leavesC" as Int
//        println(tempValue.length)  //会抛出异常 ClassCastException





        val list3 = listOf(10, 20, 30, 40)
        for (i in list3) {
            println(i)
        }

         println(2 in list3)
         println( list3.contains(2))
        println(20 in list3)
        println( list3.contains(20))


      val map = mapOf<String,Int>("方" to 0)
       val value0= map["方"]
      val   value1= map["明"]

        println( "$value0"+"$value1")

      val mutavleMapOf=  mutableMapOf<String,Int>("方" to 0)
        mutavleMapOf["明"] = 1
        mutavleMapOf["飞"] = 2
        println( "$mutavleMapOf")


        val x=3
        val y=2
        x.compareTo(y)>0

    //定义一个带名称的函数
  fun haha1(){
       println( "哈哈1")
   }

 haha1()




  println( int2Long(5) )


   //定义一个匿名函数 赋值给一个变量haha
 val haha2:()->Unit=fun(){
        println( "哈哈2")
   }
 haha2()


val  haha3:(String)->Unit={str:String-> println(str)}
    haha3("哈哈31")
        haha3.invoke("哈哈32")
   val  haha4:(String)->String={str:String->
         println(str)
       "$str 哈哈42" //最后一行表示返回值
       }
    haha4("哈哈4")


 val printlnHello={  println("hello1") }
  printlnHello()
  printlnHello.invoke()



// (Int, Int) -> Int

val sum= {arg1:Int,arg2:Int->
    println("$arg1 + $arg2 = ${arg1 + arg2}")
}
   sum(3,5)
   sum.invoke(3,5)

   val printlnHello2= {_:Unit? ->println("hello2") }


        val list4 = listOf(10, 20, 30, 40)
         list4.forEach ForEach@{e:Int->print(e)}
       // list4.forEach {println(it)}
        list4.forEach(::println)


  val latitude = Latitude.ofDouble1(3.0)
       latitude.ofDouble2(3.0)


   val  hello:String by lazy{
       "犊子啊"
   }



       // val yy = null
       // val xx = yy as String
     // println(xx)

 val student0:Student = Student("",1)
     println( (student0 as? Person)?.sex  )


  val list5 = listOf(1, 2, 3, 4,5,6,7,8,9,10)
        list5
            .asSequence() //赖操作
            .filter {
            println("被过滤的元素${it}")
            it%2==0
           }
            .map {
                println("组合新的元素 $it")
                it*2+1

            }
//            .flatMap {
//
//                (0 until it).asSequence()
//            }
//            .forEach {
//                println("遍历新的集合元素 $it")
//            }



  //   val newList =list5.map { it*2+3 }
   //  val   newList2=newList.map(Int::toDouble)
   //     newList2.forEach(::println)




    val student:Student = Student("明飞",1)

        student.let {  }
        student.run {  }

       val  newStudent:Student= student.also {
           it.age=100
           it.name="方"}
        println("newStudent=${newStudent.name}+${newStudent.age}")


        student.apply {  }

        list5.forEach {
          if(it==2) return@forEach
            println(it)
        }

         mutavleMapOf.forEach {
             println("${it.key}+${it.value}")
         }


    //泛型协变  泛型前加个out 变成协变
     val listt = List.Cons(1.0, List.Nil)
        val userDTO = UserDTO(
            0,
            "Bennyhuo",
            "https://avatars2.githubusercontent.com/u/30511713?v=4",
            "https://api.github.com/users/bennyhuo",
            "https://github.com/bennyhuo"
        )



  //=================================================
  //todo  高阶函数
 //当函数中只有一个函数(lambda函数):{ it.toInt() }作为参数，并且您使用了lambda表达式作为相应的参数，则可以省略函数的小括号()
//str.sumBy( { it.toInt() } ) -->str.sumBy{ it.toInt }

val testStr="abc"
val sum1 = testStr.sumBy( { it.toInt() } )
 //val sum1 = testStr.sumBy { it.toInt() }

println("高阶函数1 "+sum1)

 foo(qux = { "高阶函数2:qux"  })



        /**
         *测试高阶函数
         *
         * */
       val num1=180
        val num2=80




       val result1=num1Andnum2(num1,num2,::plus  )
       println("高阶函数3 "+result1)

      val result2 =num1Andnum3(num1,num2,::minus)
       println("高阶函数3 "+result2)


        val result3=num1Andnum4(num1,num2,{num1,num2->num1+num2}  )

        println("高阶函数3 "+result3)  //高阶函数3 260

        val result4 = num1Andnum5(num1,num2) { num1, num2->num1-num2}

        println("高阶函数3 "+result4) //高阶函数3 100




//todo =============================================================20221114
        KotlinDemo.Companion.callStaticMethod1()
        KotlinDemo.callStaticMethod2()

  }


/**
 *   inline  noinline crossinline
 *
 * */

inline  fun inlineTest(block:()->Unit,x:Int,y:Int, noinline  block2:(x:Int,y:Int)->Int):Unit{
    block.invoke()
    block2.invoke(x,y)
}



/**
 * 高阶函数
 * */
inline fun num1Andnum2(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
    val result1 = operation(num1,num2)
    return result1
}

 inline  fun num1Andnum3(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int{
        val result2=   operation.invoke(num1,num2)
        return result2
    }


    inline fun num1Andnum4(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation(num1,num2)
    inline fun num1Andnum5(num1:Int,num2:Int, operation:(Int, Int) -> Int):Int=operation.invoke(num1,num2)








/**
 *  定义与函数类型参数: operation:(Int, Int) -> Int   相对应的函数
 * */

fun plus(num1:Int,num2:Int):Int{
        return num1+num2
 }

 fun minus(num1:Int,num2:Int):Int= num1-num2


 fun  multiply(num1:Int,num2:Int):Unit{
     num1.div(num2)
 }







    fun foo(bar:Int=0,baz:Int=1,qux:()->Unit){
      println("bar="+bar+" baz="+baz+" qux="+qux)
    }



    //sumBy函数返回一个Int类型的值。并且接受了一个selector()函数作为该函数的参数
    //selector()函数接受一个Char类型的参数，并且返回一个Int类型的值.  selector:(Char)->Int
    public inline fun CharSequence.sumBy(selector:(Char)->Int):Int{
       var sum:Int=0
        for (element in this) {
            sum += selector(element)
        }
         return sum
    }







    data class UserVO(val login: String, val avatarUrl: String)
    data class UserDTO(
        var id: Int,
        var login: String,
        var avatarUrl: String,
        var url: String,
        var htmlUrl: String
    )








    sealed class List<out T> {
        object Nil : List<Nothing>(){
            override fun toString(): String {
                return "Nil"
            }
        }

       /*
       * val head: E 作为类Cons<E>的成员变量 有getter方法 返回E  叫做协变点
       *  */
        data class Cons<E>(val head: E, val tail: List<E>) : List<E>(){
            override fun toString(): String {
                return "$head, $tail"
            }
        }

        fun joinToString(sep: Char = ','): String {
            return when(this){
                Nil -> "Nil"
                is Cons -> "${this.head}$sep${this.tail.joinToString(sep)}"
            }
        }
    }


/*
* key 的 getter 返回K    叫做协变点
* value 的 getter 返回V  叫做协变点
* */
   public interface  Entry<out K,out V>{
       public val key:K
       public val value:V
   }




    val int2Long = fun(x: Int): Long {
        return x.toLong()
    }

    private fun parserType(strValue: Any) {
         when(strValue){
              is String -> println("value is String , ${strValue.length}")
             is Int -> println("value is Int ,${strValue.toLong()}")
             is Long ->println("value is Long")
             !is Long -> println("value !is Long")
             else -> println("unknown")

         }

    }


    fun checks(name: String?) {

      name.let {  }
       // println(name!!.length)
      }

    private fun getIntMeth(double: Double, long: Long): Int {
        return   (double+long).toInt(); //转为int 类型
    }

    private fun yc(double: Double, long: Long) {}




}




/*fun check(name: String?): Boolean {
    //编译器不允许不对 name 做 null 检查就直接调用其属性
    if(name!=null){
        return name.isNotEmpty()
    }else{
        return false
    }

}*/



/*fun check(name: String?): Boolean {
    //编译器不允许不对 name 做 null 检查就直接调用其属性

        return name?.isNotEmpty()


}*/







/*fun check(name: String?) {
    if (name != null) {
        println(name.toUpperCase())
    } else {
        println(null)
    }
}*/



/*fun check(name: String?) {
    println(name?.isNotEmpty())
}*/















