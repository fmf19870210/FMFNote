package com.yzy.xxkotlindemo

import android.util.Log

/**
 * Create by Fangmingfei on 2023-03-14 下午 3:00
 * Describe ：
 */
// 主构造函数：入参参数_xxx，都是临时的输入类型，不能直接使用，需要接收下来 转变成变量才能用
// _name _sex _age _info 等等，都是临时的类型，不能直接使用，需要转化成变量才能能用
// 主构造函数
class KtBase72(_name:String,_sex:Char,_age:Int,_info:String) {
   var name = _name
    get() = field // get() 不允许私有化，外部可以获取
    private set(value) {  //set私有化 外部不能调用了
        field = value
    }

  val sex=_sex
    get() = field
    //set(value) {field= value}  val 只读的，不能修改的，

   var age:Int =_age
    get() = field
    set(value) {field=value}

    val  info = _info
     get() = field



     fun show(){
        // Log.e("MainActivity",_name) //_name是临时的输入类型，不能直接用，需要接收下来 成为变量才能用
         Log.e("MainActivity","$name,$sex,$age,$info")
     }
}