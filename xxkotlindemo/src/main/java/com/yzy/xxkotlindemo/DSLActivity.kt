package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.File

/**
 * Create by Fangmingfei on 2023-03-29 下午 8:23
 * Describe ：
 */
class DSLActivity : AppCompatActivity() {



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 其实apply5函数，就是DSL编程范式，定义输入输出等规则：
        // todo 1.定义整个lambda规则标准，输入 必须是Context这个类，才有资格调用apply5函数，匿名函数lambda里面同时持有it 和 this
        // 2.定义整个lambda规则标准，输出 我们会始终返回Context本身，所以你可以链式调用
        // 然后main函数就可以根据DSL编程方式标准规则，来写具体的实现，这就是DSL编程范式
        val context:Context = Context().apply5(str="网版但", lambda = {
            // it == String == "网版但"
            println("我的it是:$it，我的this是:$this")
            this.toast("成功")
            this.toast(it)
            this.toast(this.name)
             true
        }

        )

        println("我始终是输出Context本身：$context  " + context.info)

       // 其实applyFile函数，就是DSL编程范式，定义输入输出等规则：
        // 1.定义整个lambda规则标准，输入 必须是File类，才有资格调用applyFile函数，匿名函数里面持有 fileName，data
        // 2.定义整个lambda规则标准，输出 我们始终返回File对象本身，所以你可以链式调用
        // 然后main函数就可以根据DSL编程方式标准规则，来写具体的实现，这就是DSL编程范式
        val applyFile:File = File("E:\\ihaveadreamcopy.txt").applyFile(action = { fileName, data ->
            println("你的文件名是:$fileName, 你的文件里面的数据是:$data")
        })

        println("我始终是输出File本身：${applyFile.name}")
    }
}
class Context {



    val info = "我就是Derry"
    val name = "DDD"

    fun toast(str: String) = println("toast:$str")
}
