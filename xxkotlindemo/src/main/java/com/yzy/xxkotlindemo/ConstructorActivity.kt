package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-14 下午 2:36
 * Describe ：类的主构造函数 次构造函数的使用
 *         KtBase72   KtBase73   KtBase74 KtBase75  KtBase76  KtBase77
 *
 */
class ConstructorActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG = "MainActivity"

        val p = KtBase72(_name = "张三", _age = 35, _sex = '男', _info = "学习KT语言")
        Log.e(TAG, p.toString())
        Log.e(TAG, p.name)// 张三
        // p.name="lisi"  //被私有化了，不能调用
        Log.e(TAG, p.name) // lisi

        p.show() // 张三,男,35,学习KT语言


        val p73 = KtBase73(name = "Zhangsan", info = "学习KT语言", age = 88, sex = 'M')
        p73.show()  //  Zhangsan,M,88,学习KT语言

        // 调用主构造
        KtBase74("李元霸")
        // 调用 2个参数的次构造函数
        KtBase74("张三", '男') //  2个参数的次构造函数 name:张三, sex:男

        // 调用 3个参数的次构造函数
        KtBase74("张三2", '男', 88)  //  3个参数的次构造函数 name:张三2, sex:男, age:88

        // 调用 4个参数的次构造函数
        KtBase74("张三3", '男', 78, "还在学校新语言")  //  4个参数的次构造函数 name:张三3, sex:男, age:78, info:还在学校新语言


        KtBase75() // 到底是调用哪一个构造函数，是次构造 还是 主构造 ？ 答：优先调用主构造函数
        KtBase75("李元霸2") // 调用主构造

        KtBase75("李连杰2", '男') // 调用 2个参数的次构造函数

        KtBase75("李小龙2", '男', 88) // 调用 3个参数的次构造函数

        KtBase75("李俊2", '男', 78, "还在学校新语言") // 调用 4个参数的次构造函数


        // 调用主构造
        KtBase76(username = "李四", userage = 88, usersex = '女') // 执行了init函数李四,女,88

        // 调用次构造
        KtBase76("王五")   // 次构造函数被调用了   // 执行了init函数,username=王五,userage=男,usersex=87


        // 调用次构造
        //   KtBase76("") // username空值，username=, 异常抛出

        // 调用主构造
      //  KtBase76("李四", userage = -1, usersex = 'M') // 执行了init函数,username=李四,userage=M,usersex=-1   // userage=-1,你的userage年龄不符合，异常抛出

        // 调用主构造
        KtBase76("李四四", userage = 1, usersex = '男')// 执行了init函数,username=李四四,userage=男,usersex=1


    }
}





















































