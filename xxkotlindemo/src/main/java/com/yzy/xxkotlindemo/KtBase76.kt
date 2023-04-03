package com.yzy.xxkotlindemo

import android.util.Log

/**
 * Create by Fangmingfei on 2023-03-14 下午 4:30
 * Describe ：
 */
// username: String, userage: Int, usersex: Char  临时类型，必须要二次转换，才能用
class KtBase76(username: String, userage: Int, usersex: Char )// 主构造
{

    // 相当于是Java的 {} 构造代码块
    // 初始化块  init代码块  任何构造函数 都会执行init{}
    // init{}里可以直接使用username: String, userage: Int, usersex: Char  临时类型，不需要二次转换，
     init {
        Log.e("MainActivity","执行了init函数,username=$username,userage=$usersex,usersex=$userage ")

         // require(value: Boolean, lazyMessage: () -> Any)
       //如果第一个入参参数  value=false，就会执行第二个参数:lambda函数
         require(username.isNotBlank(),lazyMessage = {"username空值，username=$username, 异常抛出"})
          require(userage>0,{"userage=$userage,你的userage年龄不符合，异常抛出" })
           require( usersex == '男' || usersex == '女') { "usersex=$usersex，性别不存在,异常抛出" }
     }


    constructor(username: String):this(username,userage=87,usersex='男'){
        Log.e("MainActivity","次构造函数被调用了")
    }


    fun show() {
        // println(username) // username用不了，username必须要二次转换，才能用
    }
}