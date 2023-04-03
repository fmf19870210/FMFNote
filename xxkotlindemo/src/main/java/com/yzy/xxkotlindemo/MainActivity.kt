
package com.yzy.xxkotlindemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt

/**
 *
 *
 *
 * 内置函数 let run apply also
 * */

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    val USER_NAME_SAVE_DB2 = "fangmingfei"
    val USER_PWD_SAVE_DB2 = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var method="Derry"
        Log.e(TAG,method())



        val producer2:Producer<TextView> = Producer<Button>()
        val textView2: Unit =    producer2.produce()

         val consumer1:Consumer<Button> = Consumer<TextView>()
             consumer1.consume(Button(this))



      //Kotlin语言的匿名函数学习
        "Derry".count()
        /**
         *    count(predicate: (Char) -> Boolean)
         *    count(predicate={it=='c'})  或者 count { it=='r' }
         *
         *
         *
         * */
      val len2:Int =  "Derry".count(predicate={it=='c'})
      val len3:Int  =  "Derry".count { it=='r' }
        Log.e(TAG, len2.toString()) //0
        Log.e(TAG, len3.toString()) //2

        methodAction1()
        val me:String = methodAction2()
        Log.e(TAG,me)

        /** 无参函数声明
         * 无参函数名: methodAction3
         * : 接入输入输出的声明
         *  () 输入(参数)声明
         *  String  输出(数据类型)声明
         * */
        // 第一步：函数methodAction3输入输出的声明
        val methodAction3:()->String

       // 第二步：对上面函数的实现
        // 匿名函数不要写return，最后一行就是返回值
         methodAction3={
             "methodAction3"
         }
        // 第三步：调用此函数
         Log.e(TAG,methodAction3()) //methodAction3




        /** 有参函数声明
         * 有参函数名: methodAction4
         * : 接入输入输出的声明
         *  (name:String, age:Int, birthday:Int,sex:Boolean) 输入(参数)声明
         *  String  输出(数据类型)声明
         * */

        // 第一步：函数methodAction4输入输出的声明
        val methodAction4:(name:String, age:Int, birthday:Int,sex:Boolean)->String

        // 第二步：对上面函数的实现
        // 匿名函数不要写return，最后一行就是返回值
        methodAction4=({name,age,birthday,_->
           "$name ,$age,$birthday"
        })

        // 第三步：调用此函数
        Log.e(TAG,methodAction4("方明飞",35,1987,true)) //输出 方明飞 ,35,1987

         /**
          * methodAction4.invoke("方明飞",35,1987,true) 与
          * methodAction4("方明飞",35,1987,true)是等价一样的
          * */
        Log.e(TAG, methodAction4.invoke("方明飞",35,1987,true)) //输出 方明飞 ,35,1987



         /**
          * 只有一个参数 可以省略形参str,但是其形参类型String不能省略
          * val methodAction5:(str:String)->String
          *  等价于
          *  val methodAction5:(String)->String
          * */
        val methodAction5:(String)->String

        methodAction5=({
            //只有传递一个参数时,it就是系统对传递的一个形参参数的默认定义,也可以自定义
            "$it"
        })
        Log.e(TAG,methodAction5.invoke("methodAction5")) //methodAction5


        /**
         * Kotlin语言的匿名函数的类型推断
         * 如果匿名函数方法名格式是 :, 必须指定 入参参数类型/无入参 和 出参返回类型
         *  eg: val methodAction61:(name:String, age:Int, birthday:Int,sex:Boolean)->String= { name, age, birthday, _-> "$name ,$age,$birthday"
         * 如果匿名函数方法名格式是 = ,必须指定 入参参数类型/无入参,系统会自动推断出参返回类型,不需要自己额外定义
         * (其实就是把上面匿名函数的入参括号() 和->和出参返回类型 直接给简化去掉了,只要=右边的{}即可)
            eg： val methodAction62 = {name:String, age:Int, birthday:Int,sex:Boolean->"$name ,$age,$birthday"}
         * */

        //  val methodAction61:(name:String, age:Int, birthday:Int,sex:Boolean)->String
        //   methodAction61={name,age,birthday,_-> "$name ,$age,$birthday" }
        val methodAction61:(name:String, age:Int, birthday:Int,sex:Boolean)->String =
            { name, age, birthday, _-> "$name ,$age,$birthday" }
          Log.e(TAG, methodAction61.invoke("methodAction61_方明飞",35,1987,true)) //methodAction61_方明飞 ,35,1987

          //匿名函数methodAction62自动推断出参返回类型 为String类型
          val methodAction62 = {name:String, age:Int, birthday:Int,sex:Boolean->
              "$name ,$age,$birthday"
          }
        Log.e(TAG, methodAction62.invoke("methodAction62_方明飞",35,1987,true)) //methodAction62_方明飞 ,35,1987

           //methodAction71匿名函数定义d的格式:, 无入参  无返回类型
         val methodAction71:()->Unit={
             3453.3f
         }
        Log.e(TAG, methodAction71.invoke().toString())  // kotlin.Unit

        //methodAction72匿名函数定义的格式 = , 无入参  自动推断返回类型是Float类型
        val methodAction72={
            3453.3f
        }

        Log.e(TAG, methodAction72.invoke().toString())  // 3453.3


         //匿名函数methodAction73的格式=, 入参num:Int   自动推断返回类型是Float类型
         val  methodAction73={ num:Int->
             num.toFloat()
         }
        Log.e(TAG, methodAction73.invoke(9).toString())  // 9.0

/**
 *
 * 匿名函数  属于 lambda  就是{}
 *
 * */
   //  匿名函数 入参 Int,    自动推断返回 Any类型
  //   lambda表达式的入参参数 Int, lambda表达式的自动推断返回结果Any类型
 val  weekResultMethod={num:Int->
     // lambda表达式的自动推断返回结果Any类型
     when(num){
         1 -> "星期1"
         2 -> "星期2"
         3 -> "星期3"
         4 -> "星期4"
         5 -> "星期5"
         else -> -1
     }
 }

        Log.e(TAG, weekResultMethod.invoke(9).toString()) //-1
        Log.e(TAG, weekResultMethod.invoke(2).toString()) //星期2



/**
 *   kotlin 的函数中定义了一个入参参数   这个入参参数是个函数
 *  */


   /**
    *  第一种方式
    *
    * */
loginAPI2("fangmingfei","123456",{msg: String, code: Int ->
    Log.e(TAG,"最终登录的情况如下1: msg:$msg, code:$code")
})

 /**
  * 第二种方式
第三个参数(匿名函数)(是=格式)(=左边省去了入参和出参返回类型)  可以写在 函数lonAPI2的里面
  */
        loginAPI2("fangmingfei","123456",responseResult = {msg: String, code: Int ->
            Log.e(TAG,"最终登录的情况如下2: msg:$msg, code:$code")
        })




      /**
       *   第三种方式(最简化 最常用)
      前端调用者  调用函数loginAPI2  入参  username userpwd
       通过另一个入参参数(是个匿名函数(lamuda)) 把登录状况结果返回回调给前端调用者
      第三个参数(匿名函数)  可以写在 调用函数lonAPI2的外面
       */

          loginAPI2("fangmingfei","123456"){ msg: String, code: Int ->
              Log.e(TAG,"最终登录的情况如下3: msg:$msg, code:$code")
          }


      /**
       *  函数引用
       *   lambda(匿名函数)属于函数类型的对象，那么我们就需要把methodResponseResult普通函数变成 函数类型的对象（函数引用）
       *   函数引用前面必须要添加 :: 就是把methodResponseResult普通函数变成 函数类型的对象
       *    :: methodResponseResult 就是对 函数类型的对象的调用引用
       * */
        //上面的 第一二三种方式的函数的第三个参数是通过lambda来实现的数据回调,
        // 这里的第三个参数是通过传递一个 函数引用 :: methodResponseResult ,  来实现的数据回调
        loginAPI2("fangmingfei","123456",:: methodResponseResult)


   //todo===============================================================================================================================
    /**
     * 一个(匿名)函数作为另一个函数的返回类型
     *
     * */
        //show1函数返回类型 Boolean
        var bo:Boolean =show1("学习KT语言")
        //show2函数返回类型 String
        var str2:String =show2("学习KT语言")

        // niming_showMethod 是 showMethod函数的返回类型   是一个  (匿名)函数
        val niming_showMethod = showMethod("show")  //我是showMethod函数 info:show
        // niming_showMethod 就是 匿名函数  给匿名函数传入参数 "Derry", 33
        val niming_str=  niming_showMethod("Derry", 33)
        Log.e(TAG,niming_str) // 我是showMethod函数的返回类型:匿名函数：我的name:Derry, age:33


 //todo===============================================================================================================================

/**
 * Kotlin语言的匿名函数与具名函数
 * */
        // 匿名函数{ str:String->}实现回调
        showPersonInfo("lisi", 99, '男', "学习KT语言",
            showResult={ str:String-> Log.e(TAG,"匿名函实现回调:$str") }
        )
        //输出: 匿名函实现回调:name:lisi, age:99, sex:男, study:学习KT语言



        // 具名函数 showResultImpl 实现回调
        fun showResultImpl(str: String) {
            Log.e(TAG,"具名函数实现回调:$str") //输出:具名函数实现回调:name:wangwu, age:89, sex:女, study:学习C++语言
        }
        //::showResultImpl 函数类型对象
        showPersonInfo("wangwu", 89, '女', "学习C++语言", ::showResultImpl)



   //todo===============================================================================================================================

     /**
      * Kotlin语言的可空性特点 ?声明时指定为可空类型
      * */
     // TODO 第一种情况：默认是不可空类型，不能随意给null
     var namee: String = "Derry"
        // 提示：不能是非空类型String的值
        // namee = null

        // TODO 第二种情况：?声明时指定为可空类型
        var namee2: String? =null
           namee2 = "Derry"
        Log.e(TAG,namee2)


        /**
         *  空合并操作符
         *   xxx ?: "原来你是null啊"    "如果xxx=null，就会执行 ?: 后面的代码"
         * let 函数与 空安全操作符 ?. , 空合并操作符 ?: 结合使用 , 可替代 if 语句效果 ;
         * */
        var namee3:String?=null //输出:参数name3 为null:null
            namee3 = "Derry" //输出:参数name3不为空值 :Derry
           namee3 =""//输出；参数name3 为空值,没有内容:
        // namee3是可空类型的 如果真的是null，?后面这一段代码不执行，就不会引发空指针异常
        Log.e(TAG,getName(namee3))



  //todo===============================================================================================================================
/**
 *
 * 非空断言操作符 !!
 *  !! 断言, 不管name是不是null，都执行，如果百分百能够保证name是有值的，不为null，那么才能使用断言 !!， 否则有Java 空指针异常的风险
 * */

//todo===============================================================================================================================

     
     /**
      * substring 截取函数 
      * */
       val INFO = "Derry is Success Result"
       val indexOf = INFO.indexOf('i')  //6
         Log.e(TAG, indexOf.toString())
          Log.e(TAG, INFO.substring(0, indexOf))  //Derry   //从0到indexOf角标位置
          Log.e(TAG,INFO.substring(0 until indexOf))  //Derry  //从0直到indexOf角标位置  until 直到.... 除非....


     /**
      *    split 分割函数
      * */
       val jsonText = "Derry,Leo,Lance,Alvin"
         // list 自动类型推断成 list == List<String>
            val list = jsonText.split(",")
            // 直接输出 list 集合，不解构
            Log.e(TAG,"分割后的list里面的元素有:$list") // 分割后的list里面的元素有:[Derry, Leo, Lance, Alvin]

       /**
        *replace 替换函数
        * */



        val sourcePwd = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
           Log.e(TAG,"原始密码字符串是:$sourcePwd")  // 原始密码字符串是:ABCDEFGHIJKLMNOPQRSTUVWXYZ
             //入参 regex: Regex(正则表达式),transform: (MatchResult) -> CharSequence
          val newPwd = sourcePwd.replace(Regex("[AKMNO]"),transform = {
                 Log.e(TAG,"输出原始密码字符串的每个字符:${it.value}")
                // it.value  //输出每一个字符元素
                when(it.value){ //如果字符为A,K,M,N,O替换为数字
                    "A" ->"9"                                 
                    "K"->"3"
                    "M"->"5"
                    "N"->"1"                                 
                    "O"->"4"
                    else->it.value//其他的字符不做替换
                    }
          })

       Log.e(TAG,"加密后的密码字符串是:$newPwd")  //加密后的密码字符串是:9BCDEFGHIJ3L514PQRSTUVWXYZ



      //todo===============================================================================================================================

       /**
        * Kotlin语言的==与===比较操作
        *      1. == 值 内容的比较  相当于Java的equals
        *      eg:name1 == name2   等价于 name1.equals(name2)    都是属于 值 内容的比较
        *
        *      2. === 引用的比较    只有指向常量池同一个引用对象才为true   
        *      
        * */

         val name11 : String = "Derry"
            val name22 : String = "Derry"
            val name33 = "ww"
         // 小结：name11.equals(name2)  等价于 name1 == name2  都是属于 值 内容的比较
             println(name11.equals(name22)) // java
             println(name11 == name22) // kt

           // 引用的比较
               println(name11 === name22) // true  指向常量池同一个引用对象 "Derry"
               println(name11 === name33) // false   指向常量池两个不同的引用对象  "Derry"  "ww"

          // 引用的比较 难度高一点点
             val name44 = "derry".capitalize() // 修改成"Derry"
             println(name44 === name11)  //false  虽然改为了大写的 "Derry" 但是是新开辟创建的新的引用对象 "Derry"   与上面的引用对象 "Derry" 不同


  //todo===============================================================================================================================

         
      /**
       * 字符串遍历操作
       * */
       val str_foreach = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

       str_foreach.forEach(action = {
             Log.e(TAG,"所有的字符是:$it")
       })


   //todo===============================================================================================================================

        /**
         * 数字类型的安全转换函数
         *     推荐使用 .toIntOrNull()   .toFloatOrNull()   .toDoubleOrNull()   如果转换失败  返回null 不会崩溃 
         *
         * eg: "666.6".toInt()    使用 .toInt() Double类型，无法转换成Int   转换失败  会崩溃 
         *     "666.6".toIntOrNull()   .toIntOrNull()  Double类型，无法转换成Int   转换失败 返回 null 不会崩溃 
         *     "888".toIntOrNull()   .toIntOrNull()   Int类型转换成Int 成功  
         *
         *
         * */

         val result1 : Double? =   "888.8".toDoubleOrNull()
        Log.e(TAG,"$result1") //888.8

         val result2 : Int? =   "888.8".toIntOrNull()
        Log.e(TAG,"$result2") //null


        //todo===============================================================================================================================

        /**
         * Double转Int与类型格式化
         *   用roundToInt()函数，保证 Double ->转Int 持有四舍五入的效果
         *
         *
         * */
        Log.e(TAG, 65.4645654.roundToInt().toString()) // 65 四舍五入
        Log.e(TAG, 65.8343433.roundToInt().toString()) // 66 四舍五入
        Log.e(TAG,  "%.2f".format(65.8343433))  // 65.83 保留2位小数
        Log.e(TAG,  "%.4f".format(65.8343433))  // 65.8343 保留4位小数

        //todo===============================================================================================================================


        /**  apply内置函数
         *  apply(block: T.() -> Unit)
         * apply函数始终是返回是调用者对象本身,所以可以链式调用多次apply函数
         *   apply函数的入参匿名函数的表达式{}会默认持有当前this (就是调用者对象本身)(注意:不是it )
         *   (一般大部分情况下，匿名函数的表达式{}里都会持有一个it  )
         * 无论调用多少次apply函数返回结果值无法改变调用者对象本身
         * */
  val infoNew =   "Derry You Hao".apply {
            Log.e(TAG,  "apply匿名函数里面打印this=$this")  // apply匿名函数里面打印this=Derry You Hao

             //把this (调用者对象) 全部转为小写
             this.toLowerCase()
            Log.e(TAG,"把调用者对象全部转成小写是:${toLowerCase()}") //把调用者对象全部转成小写是:derry you hao
    }.apply {
            Log.e(TAG,"调用者对象的长度:${this.length}")  //调用者对象的长度:13
     } .apply {
            Log.e(TAG,"调用者对象的第一个字符是:${this[0]}") //调用者对象的第一个字符是:D
        }  .apply {
            Log.e(TAG,"调用者对象的最后一个字符是:${this[length -1]}") //调用者对象的最后一个字符是:o
        }
        //.toUpperCase() //todo 此函数非apply() 会改变调用者对象本身


    Log.e(TAG, "apply返回的值:$infoNew") //apply返回的值:Derry You Hao  调用者对象本身并没有发生改变


        /**
         * let内置函数
           let(block: (T) -> R)  匿名函数  block: (T) -> R
         *
         *  每次调用let   函数的返回结果值和返回类型    是有   匿名函数的表达式{}的最后一行的结果值  决定的
         *let 函数里面的匿名函数的表达式{}里面默认的it  就是调用者对象本身
         *  let函数会对调用者对象本身发生改变
         * */
   val result22 = listOf(6, 5, 2, 3, 5, 7).let(block = {
            // it == list集合
            it.first()+it[0] // 匿名函数的最后一行，作为返回值，
        } )
        Log.e(TAG, result22.toString())  // 12

   /**
    * let 函数与 空安全操作符 ?. , 空合并操作符 ?: 结合使用 , 可替代 if 语句效果 ;
    * */
   var name3:String?=null //输出:参数name3 为null:null
     name3 = "Derry" //输出:参数name3不为空值 :Derry
     name3 =""//输出；参数name3 为空值,没有内容:
    Log.e(TAG,getName(name3))  // 参数name3 为空:null



  /**
   * run内置函数
   * run(block: T.() -> R)
   *  run函数的入参匿名函数的表达式{}会默认持有当前this (就是调用者对象本身)(注意:不是it )
   * 每次调用run  函数的返回结果值和返回类型    是有   匿名函数的表达式{}的最后一行的结果值  决定的
   *
   *  run函数的链式多次调用 总是把上一次调用 run函数返回的结果值自动传递给下一个 调用run函数
   * */
  val ret = "Hello".run {
      Log.e(TAG,  "run匿名函数里面打印this=$this") //run匿名函数里面打印this=Hello
       true
  }
        Log.e(TAG, ret.toString()) //返回  true

        val ret2 = "Hello".run {
            123
        }
        Log.e(TAG, ret2.toString())  //返回 123


        val ret3 = 11.45.run(block= {
            this.toString()
        })
        Log.e(TAG, ret3.toString()) //返回 11.45


        val ret4 = "Hello".run(
            block = { this.plus(" World") }
        )
        Log.e(TAG,ret4) //Hello World


         /**
         *  run函数  配合  具名函数 多次调用run
        调用者对象.run(::具名函数())
         */


        fun isLong(str: String): Boolean {
            Log.e(TAG,"输出具名函数isLong的参数=$str")  // 输出具名函数isLong的参数=Derry is OK
           return    if(str.length>5){true}else{false}
        }

          //第一次调用run  使用具名函数isLong 传入的参数 是 调用者对象本身 it == str本身
       var result: Boolean =  "Derry is OK".run(::isLong)

        fun showText(isLong: Boolean): String {
            Log.e(TAG,"输出具名函数showText的参数=$isLong") // 输出具名函数showText的参数=true
          return  if (isLong) "你的字符串合格" else "你的字符串不合格"
        }

        //第二次调用run  使用具名函数showText 传入的参数是 上面返回的结果值 result: Boolean
       var result222 =  result.run(::showText)


        fun mapText( str: String): String {
            Log.e(TAG,"输出具名函数mapText的参数= $str") //输出具名函数mapText的参数= 你的字符串合格
            return str.plus("  123")
        }
     var result3  =   result222.run(::mapText)
        Log.e(TAG,result3) //你的字符串合格  123


        /**
         *  run函数  配合  匿名函数  多次调用run
         * */
        "haha".run(block= {
            Log.e(TAG,"输出当前匿名函数的入参参数=$this") // 输出当前匿名函数的入参参数=haha
            if (this.length > 5) true else false
        })
            .run {
                Log.e(TAG,"输出当前匿名函数的入参参数=$this") //输出当前匿名函数的入参参数=false
                if (this) "你的字符串合格" else "你的字符串不合格"
            }
            .run {
                Log.e(TAG,"输出当前匿名函数的入参参数=$this") //输出当前匿名函数的入参参数=你的字符串不合格
                123
            }
            .run{
                Log.e(TAG,"输出当前匿名函数的入参参数=$this") //输出当前匿名函数的入参参数=123
            }












/** with内置函数
 *
 *
 * with 函数 与 run 函数 功能是一样的
 *   (1)with函数的入参匿名函数的表达式{}会默认持有当前this (就是调用者对象本身)(注意:不是it )
 *   (2)每次调用with函数的返回结果值和返回类型    是有   匿名函数的表达式{}的最后一行的结果值  决定的
 * 其使用形式不同 , with 函数是 独立使用的 ,
 * 调用时 , 需要 将 被调用者对象 作为 with 函数的 参数 ;
 * with(receiver: T, block: T.() -> R)
 * */

 val str = with("hello",block = {this.capitalize()})
        println(str) //Hello
 //等同于run()
     val str1 = "hello".run(block = {this.capitalize()})
        println(str1) //Hello


        val str3 = with("hello",block = {this.plus("world")})
        println(str3) //helloworld

        val str4 = "hello".run(block = {this.plus("world")})
        println(str4) //helloworld

       val str5  = with(10,block = {this.minus(5)})
        println(str5) //5
        val str6 = 10.run(block = {this.minus(5)})
        println(str6) //5


         /**
          *  with的具名函数操作
          *  */
        fun getStrLen( str: String): Int {

          return   str.length
        }

         //具名函数getStrLen传递的参数就是 this=str=调用者对象本身="李元霸 "
        val r1:Int = with("李元霸", ::getStrLen)

        fun getLenInfo(len: Int): String {
          return  "你的字符串长度是:$len"
        }

        val r2:String = with(r1, ::getLenInfo)

        fun getInfoMap(info: String): String {
             return "$info"
        }

        val r3 = with(r2, ::getInfoMap)
        Log.e(TAG,"输出最后一次调用with函数的结果值  =  $r3") //  输出最后一次调用with函数的结果值  =  你的字符串长度是:3


        /**
         *  with的匿名函数操作
         *  */

        with(with(with(with(with("李元霸") {
            length
        }) {
            "你的字符串长度是:$this"
        }){
            "【$this】"
        }){
            true
        }){
            println(this)
        }



        /**  also内置函数
         *  also(block: (T) -> Unit)
           also 函数 将 函数调用者本身 作为参数传递给 Lambda 表达式参数 , 所以also 函数里面的匿名函数的表达式{}里面默认的it  就是调用者对象本身
         *
         *  also 函数始终返回的是 接收者实例对象本身,可以多次调用also 函数链式调用。(apply函数一样)
         *
         *
         *   also 函数 与 let 函数 返回值不同:
            also 函数 始终返回 函数调用者 对象本身 ,
            let 函数 返回 Lambda 表达式的最后一行 ;
         *
         * */
         val alsoStr = "fangmingfei".also {
            Log.e(TAG,"输出also函数的it=$it")//这里it 就是指 also()函数调用者对象本身 "fangmingfei"

            it.plus("1987")
            354543.4f
            454
            'C'
            true
         }
        // 最终打印的是最初的 接收者对象本身 fangmingfei 而不是  Lambda 表达式里的其他的 值
         println(alsoStr)

        val alsoStr2 = "fangmingfei".also {
            it.plus("1987")
        }.also { it.capitalize() }
        // 最终打印的是最初的 接收者对象 fangmingfei 不是 fangmingfei1987 不是 Fangmingfei
        println(alsoStr2)


     val alsoStr3 =   "FANGMINGFEI".also {
         Log.e(TAG,"输出also函数的it=$it") //输出also函数的it=FANGMINGFEI
         it.plus("123")
     }.also {
         Log.e(TAG,"将调用者对象it=$it,转换小写的效果是:${it.toLowerCase()}") // 将调用者对象it=FANGMINGFEI,转换小写的效果是:fangmingfei
         it.toLowerCase()
     }.also {
         true
     }
        Log.e(TAG,"最终输出alsoStr3结果值还是调用者对象本身=$alsoStr3,并没有发生改变") // 最终输出alsoStr3结果值还是调用者对象本身=FANGMINGFEI,并没有发生改变

/**
 * takeIf内置函数
 *  takeIf(predicate: (T) -> Boolean)
 * takeIf 函数用于是true的场景
 * takeIf 函数 的 返回值 由其 Lambda 表达式参数的返回值 确定 ,
 *  如果 takeIf 函数 的Lambda 表达式{} 返回 true , 则 返回 调用者对象本身 ;
    如果 takeIf 函数 的Lambda 表达式 {}返回 false , 则 返回 null 空值 ;

 name.takeIf { true/false }
true: 直接返回调用者对象name本身
false: 直接放回null

 * takeIf函数 可以 直接 作用于 接收者对象 , 非常适合进行 函数式编程 的 链式调用 场景
 *
 * */

     val takeIfStr =  "fang".takeIf {
    // takeIf 函数 的的Lambda 表达式{it.contains("f") =true} 返回接收者调用者对象本身fang，则可以继续进行后面的 调用者对象的链式调用
         it.contains("f")
         }?.plus("ming")?.capitalize()

        Log.e(TAG,takeIfStr!!)  //Fangming

        // takeIf真正的用途


         val takeResult =  checkPermissionAction2("Root1", "!@#$")
            Log.e(TAG,takeResult) //Root





/**
 * takeUnless内置函数
 *
 * takeUnless 函数用于是false的场景
 * takeUnless 函数 与 takeIf 函数 效果正好相反 ;

takeUnless 函数 的 返回值 由其 Lambda 表达式参数的返回值 确定 ,
如果 takeUnless 函数 的Lambda 表达式{} 返回 false , 则 takeUnless 函数返回 接收者对象本身 ;
如果 takeUnless 函数 的Lambda 表达式{} 返回  true , 则 takeUnless 函数返回 null 空值 ;

name.takeUnless { true/false }?:""
false:返回name本身，
true返回null,执行  ?:后面的语句

takeUnless+it.isNullOrBlank() 一起使用，可以验证字符串有没有初始化等功能
string.takeUnless{it.isNullOrBlank()}?"字符串未进行初始化"
 */

val takeUnlessStr = "derry".takeUnless {
   // takeUnless 函数 的的Lambda 表达式{it.contains("f") =false} 返回接收者调用者对象本身derry，则可以继续进行后面的 调用者对象的链式调用
     it.contains("f") //=false
}?.plus(" teacher")?.capitalize()
         println(takeUnlessStr) // 输出 Derry teacher

        var infoValue: String? = null
        //infoValue=""
       // infoValue="fff"
    val    takeUnlessStr2 = infoValue.takeUnless {
        Log.e(TAG,"判断调用者对象it=$it,调用者对象不存在=${it.isNullOrBlank()}")  //判断调用者对象it=null,调用者对象不存在=true
         it.isNullOrBlank()  //=true  那么 takeUnless 函数 返回null
    }?:" 调用者对象infoValue=$infoValue,未经过任何初始化值"

        Log.e(TAG,takeUnlessStr2) //调用者对象infoValue=null,未经过任何初始化值





    }





    private fun checkPermissionAction2(username: String, userpwd: String): String {
       return username.takeIf { username == "Root" && userpwd == "!@#$" }?:"输入用户名和密码错误"
    }



    // namee3是可空类型的 如果真的是null，?后面这一段代码不执行，就不会引发空指针异常
    private fun getName(name3: String?): String {
       //如果 name3 为null, 则 name3?.let {...} 为空 ,   let 函数根本不会执行 ,就不会引发空指针异常, 此时会取 空合并操作符 ?: 后面的值作为整个表达式的值 ;
      //如果 name3 不为空  不为null, 则 执行 let 函数 , 整个表达式的值  就是 let 函数的最后一行 返回值 ;
        val result  = name3?.let {
            // it == name3 本身 ，如果能够执行到这里面的，it 一定不为null

            if(it.isBlank()){ // 如果name3是空值 "" 没有内容
                "参数name3 为空值,没有内容:$it"
            }else{
                "参数name3不为空值 :$it"
            }
      }?:"传递的参数name3 为null:$name3"
      return result
    }


    private fun method()="Derry"

    private fun methodAction1() {
         Log.e(TAG, "methodAction1")
    }


    private fun methodAction2(): String {
         return  "methodAction2"
    }





    /**
     * 内联inline 函数
     * 如果某个函数的其中一个入参参数是lambda(匿名函数),那么该函数就需要声明为内联函数  添加关键字inline
     * 如果此函数，使用内联inline，相当于 C++ #define 宏定义 宏替换，会把代码替换到调用处，在调用处 没有任何函数开辟 对象开辟 的损耗 达到内部的优化
     * 如果此函数，不使用内联，在调用端调用该函数时，会生成多个对象来调用lambda函数（就会造成性能损耗）
     * 如果此函数没有使用lambda(匿名函数)作为参数，那么该函数就不需要声明成内联inline
     * */
    public  inline  fun loginAPI2(username: String, userpwd: String, responseResult:(String,Int)->Unit) {
        if (username == null || userpwd == null) {
            TODO("用户名或密码为null") // 出现问题，终止程序
        }

        // 做很多的校验 前端校验
        if (username.length > 3 && userpwd.length > 3) {
             if(username==USER_NAME_SAVE_DB2&&userpwd==USER_PWD_SAVE_DB2){
                 // 登录成功
                 // 做很多的事情 校验成功信息等
                 // 通过参数 lamuda函数(匿名函数) responseResult把登录成功的信息回调给前端调用者
                 responseResult.invoke("login success", 200)
             }else{
                 // 登录失败
                 // 做很多的事情 登录失败的逻辑处理
                 // 通过参数 lamuda函数(匿名函数) responseResult把登录失败的信息回调给前端调用者
                 responseResult.invoke("login error", 444)
             }
        }else{
            TODO("用户名和密码不合格") // 出现问题，终止程序
        }



    }




    private fun methodResponseResult(msg: String, code: Int) {
        Log.e(TAG,"最终登录的情况如下4: msg:$msg, code:$code")
    }



    //返回类型 Boolean
    private fun show1(info: String): Boolean {
        Log.e(TAG,"我是show1函数 info:$info")
        return true
    }

    //返回类型 String
    fun show2(info: String): String {
        Log.e(TAG,"我是show2函数 info:$info")
        return "DDD"
    }

/**
 *  showMethod(info: String)函数 的返回类型是一个 匿名函数(String, Int) -> String (这个匿名函数有入参String,Int  返回类型String)
 *  */
    private fun showMethod(info: String): (String,Int)->String {
    Log.e(TAG,"我是showMethod函数 info:$info")
    // return 返回一个匿名函数
    return {name:String,age:Int->
        "我是showMethod函数的返回类型:匿名函数：我的name:$name, age:$age"
    }
    }




    private fun showPersonInfo(name: String, age: Int, sex: Char, study: String,  showResult: (String) -> Unit) {
        val str :String= "name:$name, age:$age, sex:$sex, study:$study"
         showResult.invoke(str)
    }




}
















