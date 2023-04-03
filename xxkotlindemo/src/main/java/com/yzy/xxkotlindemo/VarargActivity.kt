package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-24 下午 9:58
 * Describe ： vararg 动态参数的学习
 *            运算符重载 operator的学习
 */
class VarargActivity  : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 由于你使用的 太多类型的混合了，泛型 这个才是他真正的类型 : KtBase107<{Comparable<*> & java.io.Serializable}>
        //  由于不允许我们这样写 : KtBase107<{Comparable<*> & java.io.Serializable}> 所以我们用父类 Any? 代替
        // vararg objects : T 动态类型参数 可以传递很多不同类型的参数
        val p:KtBase107<Any?>  = KtBase107(isMap = true,"Derry", false, 53454, 4543.3f, 4554.54, null, 'C')

           println(p.showObj(0)) // Derry
        println(p.showObj(1))
        println(p.showObj(2))
        println(p.showObj(3)) // 4543.3
        println(p.showObj(4)) // 4554.54
        println(p.showObj(5)/*?.特殊操作 如果是null 会引发奔溃*/) // null
        println(p.showObj(6)) // C
       // println(p.showObj(17))

      val mapedResult =   p.mapObj(0,mapAction = {

                println("输出当前对象it=$it") // 输出当前对象it=Derry
                 it
          // it的类型  实际上 真正的类型 {Comparable<*> & java.io.Serializable}  需要转换一下才行 例如：it.toString
                it.toString().length //最后一行返回结果
         })

        println("最终输出结果 $mapedResult")//最终输出结果 5


        // it的类型  实际上 真正的类型 {Comparable<*> & java.io.Serializable}  由于我们的第三个元素是 Int类型，所以不需要转换，自动转的
        val  r2 =  p.mapObj(2) {
          "我的第三个元素是:$it"
      }

        println(r2) // 我的第三个元素是:53454


        println(inputObj("Derry")) //5
        println(inputObj("123")) //3
        println(inputObj(null)) //  你个货传递的泛型数据是null啊

        //泛型INPUT=Any？类型
        // INPUT 类型 有String类型  有int类型  有boolean类型  有null
        val  p1:KtBase108<Any?> = KtBase108(isTrue = true,"张三", "李四", true,123,null)
        var r : Any?= p1[0]
        println(r) // 张三
        var r22:Any?=p1[2]
        println(r22) // true
        var r3:Any?=p1[3]
        println(r3) //123
        var r4:Any?=p1[4]
        println(r4) // null


    }


}

// vararg 动态参数 表示可以传递很多个参数
// 纠正： 为什么 it 是 String ? , 是因为你的动态参数数组可以传递一个null参数
// 是因为你的  lambda (T ?) -> O  T? 指定了 ？
class KtBase107<T>(var isMap: Boolean =true, vararg objects:T){

    // 1.objectArray:Array<out T> 参数数组
    // out 我们的T只能被 读取，不能修改   T只能读取
    private val objectArray:Array<out T> = objects


    // 2.showObj(index) 返回具体的类型
    //这里不能使用objects,objects只是临时参数,要使用objectArray
    //isMap=true 返回 objectArray[index]:T  isMap=false 返回null
    fun  showObj(index:Int):T? = objectArray[index].takeIf { isMap }?:null


    // 3.变换类型mapObj(index, 变换lambda)   objectArray[index]

    fun <O> mapObj(index:Int,mapAction:(T?)->O):O? {
           //isMap=true 返回 objectArray[index]:T  isMap=false 返回null
           val  inPutType :T?= objectArray[index].takeIf { isMap }
         //如果  入参 inPutType=T  mapAction.invoke(inPutType) 返回O
        // 如果  入参 inPutType=null  mapAction.invoke(inPutType) 返回null
          val mapedResult:O? = mapAction.invoke(inPutType)
         return  mapedResult
    }


}



class KtBase108<INPUT>(var isTrue: Boolean =true, vararg objects:INPUT){

    // 开启INPUT泛型的只读模式
    private val objectArray: Array<out INPUT> = objects

   // 5种返回类型变化的解释
       // 返回数组类型 Array<out INPUT>
       // 如果isTrue=true 返回数组 Array<out INPUT>类型   isTrue=false   返回null
      fun  getR1(): Array<out INPUT>? =   objectArray.takeIf { isTrue }

     // 返回Any类型
     // 如果isTrue=true 返回数组 Array<out INPUT>类型   isTrue=false   返回String类型 ("返回数据为null")
       fun getR2() :Any = objectArray.takeIf { isTrue }?:"返回数据为null"
    // 返回Any?类型
    fun getR3() : Any? = objectArray.takeIf { isTrue } ?: "你是null了" ?: null

   //返回 INPUT ? 类型
    // 如果isTrue=true  返回 objectArray[index]:INPUT类型  如果  isTrue=false  返回null
    fun getR4(index:Int):INPUT?{
       return objectArray[index].takeIf { isTrue }?:null
    }

    //对 get
    // 对系统提供的operator fun get(index: Int): T 进行重载 进行自定义
    //返回 INPUT ? 类型
    // 如果isTrue=true  返回 objectArray[index]:INPUT类型  如果  isTrue=false  返回null
    operator  fun get(index:Int):INPUT?{
        return objectArray[index].takeIf { isTrue }
    }

}





