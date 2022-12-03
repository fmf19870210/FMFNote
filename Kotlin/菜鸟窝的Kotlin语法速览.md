## Kotlin语法速览

#### 一、基础语法



https://www.jianshu.com/nb/46140987

https://github.com/zhiwei1990/android-jetpack-demo

> .kt
>
> .java 一个public的class

> toplevel 
>
> .kt，变量，常量，类，多个。混合。

- 变量

  ```kotlin
  var a:Int =2//int Int,Integer,Double,double
  var a = 3
  val π2 = 6
  var ss:String?=null
  lateinit var str:String//不能null，必须引用类型。
  val sstr:String by lazy{ "by lazy string" }/* hhh */
  const val π = 3.14
  ```

- 函数

  ```kotlin
  fun sum(a:Int,b:Int):Int{//= a+b
    return a+b
  }
  
  fun div(c:Int,d:Float = 1f){
    //...
  }
  
  fun more(vararg a:Int) :Int{
    a.get(0),a.get(1),a[2]
    //,,,,,
    return 999
  }
  /**
  * 注释
  //
  /**/
  */
  fun main(args:String){
    val sum = sum(1,2)
    val ss = div(2)
    more(2,35,23)
    //打印
    println(sum)
  }
  ```

- 语法

  ```kotlin
  //for,when
  fun main(args:String){
    for(i in 0..10){
      println(i)
    }
    for(i in 0 until 10) println(i)
    for(i in 0..10 step 2)println(i)
    for(i in 10 downTo 0 step 2)println(i)
    
    //when
    var i:Type
    when(i){
      i is String->{}
      i !is Float->{}
      i in 0..10->{}
      i==2->{}
      else->{}
    }
    when{
      boolean->{}
      else->{}
    }
  }
  ```

  

- 类

  - class

  ```kotlin
  class Aclass(){
    //属性
    var name:String = ""
    lateinit var b:String
    //函数
    fun getSomeInfo():String{
      this::b.isInitialized//boolean
      return ""
    }
  }
  interface AAA{
    fun aaa()
    fun bbb():Int
    fun ccc(){
      
    }
  }
  ```

  - object

  ```kotlin
  object Obclass{
    //成员函数。属性，单例静态函数
    fun aaa(){}
  }
  //
  Obclass.aaa()
  ```

  - data class

  ```kotlin
  data class AAA(var name:String,var age:Int = 20){
    var grade:String = ""
  }
  val aa = AAA("hhh",98)
  ```

  - sealed class

  ```kotlin
  //密封类，类似枚举类型作用 kt
  sealed class Slclass()
  data class Ac(var a:Int) :Slclass
  object Obc:Slclass
  ```

  

- 构造函数

  ```kotlin
  class AAA(private var b:Int=9)
  class AAA private constructor(val a:Int){
    val ccc = a
    fun aaa(){
      a
    }
  }
  class AAA{
    constructor()
    constructor(var b:Int){
      //kkkkk
    }
    constructor(var a :Int,var b:String="")
    init{
      //init some data
    }
  }
  ```

- 高阶函数

  ```kotlin
  //also,with,let,apply,run,
  var s:String?=null
  s?.also{it->
    
  }
  
  s?.let{it->
    
  }
  //this->仅仅为了看清楚。
  s?.apply{this->
    length
  }
  with(s){this->
    ?.
  }
  s?.run{this->
    
  }
  //typealias
  typealias ob = java.Observer
  
  //take,takeunless,takeif,runcatching,group,map,flatmap,last,first,.....
  ```

  