package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-13 下午 2:42
 * Describe ：类里面字段属性的 get set 定义以及使用
 */
class FieldActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = "MainActivity"

        // 背后隐式代码： new KtBase70().getName()
        val str = KtBase70().name  // 参数name3不为空值 :Derry
        Log.e(TAG, getName(str))

        // 背后隐式代码：new KtBase70().setName("Kevin");
        KtBase70().name="Kevin"
        Log.e(TAG,getName(KtBase70().name)) //  参数name3不为空值 :Derry


        // 背后隐式代码：new KtBase70().getNumber()
        Log.e(TAG, KtBase70().number.toString()) //0

        // 背后隐式代码：new KtBase70().setNumber(9);
       // KtBase70().number=9 // val 根本就没有 setXXX函数，只有 getXXX函数


        // 背后隐式代码： new KtBase70().getNumber2()
      var strnumber2   =  KtBase70().number2
        Log.e(TAG,strnumber2.toString()) //71   50


// 背后隐式代码： new KtBase70().getShowInfo()
       var info = KtBase70().info
        Log.e(TAG, KtBase70().getShowInfo(info)) // 当前字段不存在，为null:null

        KtBase70().info="fangmingfei"
        var info2 = KtBase70().info
      Log.e(TAG, KtBase70().getShowInfo(info2))




    }
}



class KtBase70{
    var name:String="Derry"
        //  get() set(value) 是隐式的写不写都有 可以不写
        get() = field
        set(value) {
            field = value
        }

    /* 背后做的事情：等同于java的类的字段属性的getXXX() setXXX()

@NotNull
private String name = "Derry";

public void setName( @NotNull String name) {
     this.name = name;
}

@NotNull
public String getName() {
     return this.name;
}

*/



    val number : Int = 0
/* 背后的代码：

       private int number = 0;

       public int getNumber() {
            return this.number;
       }

     */



    // 计算属性  下面这样写 get函数覆盖了 field 内容本身，相当于field失效了，无用了，以后用不到了
    val number2 : Int
    // 自定义get()返回数据
        get() = (1..1000).shuffled().first()

    /*
        背后隐式代码：

        为什么没有看到 number2 属性定义？
        答：因为属于 计算属性 的功能，根本在getNumber2函数里面，就没有用到 number2属性，所以 number2属性 失效了，无用了，以后用不到了

         public int getNumber2() {
            return (1..1000).shuffled().first()java的随机逻辑 复杂 ;
       }
     */

    var info: String ? = null

    // 防范竞态条件  当你调用成员，这个成员，可能为null，可能为空值，就必须采用 防范竞态条件，这个是KT编程的规范化
    fun  getShowInfo(info:String?):String{
        // 这个成员info，可能为null，可能为空值""，就启用 防范竞态条件
        // 这种写法，就属于 防范竞态条件，我们可以看到专业的KT开发者，有大量这种代码
        // also永远都是返回 info本身

         val result =  info?.let {
              if(it.isBlank()){
                 "当前字段为空值,为'' : $it" // 是根据匿名函数最后一行的变化而变化
              }else{
                  "当前字段不为空值:$it" // 是根据匿名函数最后一行的变化而变化
              }
         }?:"当前字段不存在，为null:$info" // 是根据匿名函数最后一行的变化而变化

       return  result
   }


}


































