package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解

/**
 * Create by Fangmingfei on 2023-04-01 下午 8:54
 * Describe ：  懒汉式的实现 双重校验安全  KT版本
 */
class SingletonDemo4Kt private constructor() {
    companion object {

        val instance: SingletonDemo4 by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { SingletonDemo4() }

    }


    fun show() {
        println(" SingletonDemo4Kt show")
    }

}






















