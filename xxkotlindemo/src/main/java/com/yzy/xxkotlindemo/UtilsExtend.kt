package com.yzy.xxkotlindemo

import com.yzy.xxkotlindemo.rxjava.RxJavaCoreClassObject
import java.io.File

/**
 * Create by Fangmingfei on 2023-03-13 下午 3:06
 * Describe ： 扩展文件
 */


// namee3是可空类型的 如果真的是null，?后面这一段代码不执行，就不会引发空指针异常
public fun getName(name3: String?): String {
    //如果 name3 为null, 则 name3?.let {...} 为空 ,   let 函数根本不会执行 ,就不会引发空指针异常, 此时会取 空合并操作符 ?: 后面的值作为整个表达式的值 ;
    //如果 name3 不为空  不为null, 则 执行 let 函数 , 整个表达式的值  就是 let 函数的最后一行 返回值 ;
    val result  = name3?.let {
        // it == name3 本身 ，如果能够执行到这里面的，it 一定不为null

        if(it.isBlank()){ // 如果name3是空值 "" 没有内容
            "参数name3 为空值,为'':$it"
        }else{
            "参数name3不为空值 :$it"
        }
    }?:"传递的参数name3不存在,为null:$name3"
    return result
}



fun <B>  show2(item:B?){
     item?.also {
         if(it!=null){
             println("当前对象it是=$it")
         }else{
             println( "当前对象不存在,为null=$it")
         }
     }?: println("当前对象不存在为null=$item")


}




inline  fun <I,O>  map(inputValue:I,isMap:Boolean=true,mapActionLambda:(I)->O){
     if(isMap){
           mapActionLambda.invoke(inputValue)
     }else null
}


/**
 泛型是很大的范围类型，可以接收很多不同类型，当然也可以接收null，但是接收null后，要处理好，否则会崩溃
String?类型 够接收 "Derry" "Kevins" 还可以接收null，所以 Stirng? 比 String 功能强大
 */
 fun  <INPUT> inputObj(inputType:INPUT): Any {
     // println((item as String).length) // 在泛型中，不能这样做，这个是不标准的 传入null 会崩溃

     return (inputType as String?)?.length ?: "你个货传递的泛型数据是null啊"

}



// 1.扩展文件一般都是public，如果private外界无法使用
// 2.Iterable<E> 子类 set list 都可以用，所以用父类
// 3.本次扩展函数的作用是，随机取第一个元素返回
// this =E
fun <T> Iterable<T>.randomItemValuePrintln() {
    println("当前扩展对象T=${this}")
    println(this.shuffled().first())

}



/**
// private 私有化
// inline  我们的函数是高阶函数，所以用到内联，做lambda的优化，性能提高
// fun<I, O> 在函数中，申明两个泛型，函数泛型  I输入Input， O输出Output
// I.mLet 对I输入Input进行函数扩展，扩展函数的名称是 mLet，意味着，所有的类型，万能类型，都可以用 xxx.mLet
// lambda : (I) -> O   (I输入参数) -> O输出返回类型
//  :  O输出返回类型  会根据用户的返回不同数据类型，变化而变化
// lambda(this) I进行函数扩展，在整个扩展函数里面，this == I本身
//官方的let()   public inline fun <T, R> T.let(block: (T) ->  R): R {return block(this)}
 */
//通过扩展函数 自定义内置函数myLet
public  inline fun<I,O> I.myLet(lambda:(I)->O):O {return lambda(I@this)}


/**
 * // private私有
// inline 因为我们的函数是高阶函数，需要使用内联对 lambda进行优化处理，提高性能
// fun <INPUT> 函数中声明一个泛型INPUT
// INPUT.mApply 让所有的类型，都可以 xxx.myApply  泛型扩展函数
//  todo INPUT.() -> Unit 让我们的匿名函数lambda里面持有 this对象本身 ,在lambda里面不需要返回值，因为永远都是返回INPUT本身
// lambda(this) 默认就有this
// 返回this的目的是可以链式调用
 //官方的apply
public inline fun <T> T.apply(block: T.() -> Unit): T {
block()
return this
}
 */


// 返回INPUT 本身  对本身INPUT在进行一次扩展INPUT.()->Unit
public inline fun<INPUT> INPUT.myApply(lambda:INPUT.()->Unit):INPUT{
   //  lambda.invoke(this)
    lambda(INPUT@this) //省略this  lambda( )

    return INPUT@this
}

public inline fun<INPUT> INPUT.MyApply2(str:String,lambda:INPUT.(String)->Unit):INPUT{
      lambda(str)
    return INPUT@this
}

// 给类Context 添加扩展函数apply5()
//todo Context.(String) -> Unit 让我们的匿名函数lambda里面持有 this对象本身， 并且还需要传递一个参数String,在lambda里面不需要返回值 Unit
public inline fun Context.apply5(str:String,lambda:Context.(String)->Unit):Context{
   //把参数 str 传递给lambda匿名函数 (String)
     lambda(this,str) //本身对象this 可以省略掉  但是传递的参数 str 不可以省略 lambda(str) //  lambda.invoke(this,str)

    return this
}



public inline fun File.applyFile(action: (String,String?)->Unit):File {
      this.setWritable(true)
      this.setReadable(true)
    // action.invoke(this.name,this.readLines()[0])
      action(this.name,this.readLines()[0])
   return this
}


// lambda函数action的 入参参数:无  lambda函数action的  返回数据类型   OUTPUT
// 把  lambda函数action的  返回数据  outputValue:OUTPUT  再作为RxJavaCoreClassObject<out INPUT>(  _valueItem:INPUT)的入参参数  即  _valueItem:INPUT=outputValue:OUTPUT
inline fun <OUTPUT> create(action:()->OUTPUT):RxJavaCoreClassObject<OUTPUT>{
    val  outputValue:OUTPUT = action()
    return RxJavaCoreClassObject<OUTPUT>(outputValue)
}



// 把之前RxJavaCoreClassObject<out INPUT>(  _valueItem:INPUT) 的输入值参数 _valueItem:INPUT
//  把输入参数INPUT的对象 INPUT.()  作为 lambda函数mapAction的 入参参数:INPUT.()
// lambda函数mapAction的  返回数据类型   OUTPUT
//再把 lambda函数mapAction的  返回数据 ouputValue:OUTPUT    再作为 RxJavaCoreClassObject<out INPUT>(  _valueItem:INPUT)的入参参数    即 _valueItem:INPUT =  ouputValue:OUTPUT
inline fun<INPUT,OUTPUT> RxJavaCoreClassObject<INPUT>.map(mapAction: INPUT.()->OUTPUT):RxJavaCoreClassObject<OUTPUT>{
   //todo  注意 这里的this  =  INPUT.()对象
    val ouputValue:OUTPUT = mapAction(this.valueItem)

   return  RxJavaCoreClassObject<OUTPUT>(ouputValue)
}


 // I.() 是怎么来的呢? 表示什么意识呢?
 //I.()  就是  RxJavaCoreClassObject<out INPUT>(  _valueItem:INPUT) 的输入值参数 _valueItem:INPUT
// 即  _valueItem:INPUT 就是I.()
//TODO    lambda函数observerAction的入参参数:  _valueItem:INPUT= I.()   lambda函数observerAction的返回值为 Unit
inline fun <INPUT>  RxJavaCoreClassObject<INPUT>.observer(observerAction:INPUT.()->Unit):Unit{
     //TODO  this = I.() =  _valueItem:INPUT 的对象
      observerAction.invoke(this.valueItem )
}



















