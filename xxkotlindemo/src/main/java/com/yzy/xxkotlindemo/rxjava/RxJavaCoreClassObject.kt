package com.yzy.xxkotlindemo.rxjava

/**
 * Create by Fangmingfei on 2023-04-03 下午 3:11
 * Describe ：
 */
// 中转站，保存我们的记录
class RxJavaCoreClassObject<out INPUT>(  _valueItem:INPUT) { // 主构造，接收你传递进来的信息数据(类型泛型T)，此信息数据就是create最后一行的返回的数据(类型泛型T)
// valueItem == create操作符 的最后一行的返回值  流向此处了

   // override fun toString(): String {
     //   return  valueItem.toString()
   // }
 val valueItem :INPUT  =  _valueItem

    fun get():INPUT{
        return valueItem
    }

}