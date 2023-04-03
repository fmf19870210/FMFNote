package com.yzy.xxkotlindemo

import android.util.Log

/**
 * Create by Fangmingfei on 2023-03-14 下午 3:40
 * Describe ：
 */
// var name: String  就相当于  var name = _name  这不过你看不到而已
// 一步到位，不像我们上一篇KtBase72是分开写的
class KtBase73(var name: String, val sex: Char, val age: Int, var info: String) {


    fun show(){
        // Log.e("MainActivity",_name) //_name是临时的输入类型，不能直接用，需要接收下来 成为变量才能用
        Log.e("MainActivity","$name,$sex,$age,$info")
    }
}