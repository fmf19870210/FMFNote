package com.yzy.xxkotlindemo

import android.util.Log

/**
 * Create by Fangmingfei on 2023-03-14 下午 3:56
 * Describe ：
 */
class KtBase74 (name: String) // 主构造
{


// 2个参数的次构造函数，必须要调用主构造函数，否则不通过，
// 为什么次构造必须调用主构造？答：主构造统一管理 为了更好的初始化设计
constructor(name: String, sex: Char) : this(name) {
    Log.e("MainActivity","2个参数的次构造函数 name:$name, sex:$sex")
}

    // 3个参数的次构造函数，必须要调用主构造函数
    constructor(name: String, sex: Char, age: Int) : this(name) {
        Log.e("MainActivity","3个参数的次构造函数 name:$name, sex:$sex, age:$age")
    }


    // 4个参数的次构造函数，必须要调用主构造函数
    constructor(name: String, sex: Char, age: Int, info: String) : this(name) {
        Log.e("MainActivity","4个参数的次构造函数 name:$name, sex:$sex, age:$age, info:$info")
    }


}