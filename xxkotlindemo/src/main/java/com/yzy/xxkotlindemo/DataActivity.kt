package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-18 下午 8:40
 * Describe ： 数据类data的学习
 *  使用条件
 *  条件一：服务器请求回来的响应的 JavaBean  LoginResponseBean 基本上可以使用 数据类
 * 条件二：数据类至少必须有一个参数的主构造函数
 *  条件三：数据类必须有参数， var val 的参数
 *  条件四：数据类不能使用 abstract，open，sealed，inner 等等 修饰 （数据类，数据载入的事情 数据存储）
 *  条件五：需求 比较，copy，toString，解构，等等 这些丰富的功能时，也可以使用数据类
 *
 *    默认的copy toString hashCode equals 等等...  只管输出主构造参数信息，不输出次构造的参数信息
      所以  如果你既要输出主构造参数信息  又要输出次构造的参数信息 就 必须重写 copy toString hashCode equals 函数

 */
class DataActivity  : AppCompatActivity()  {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         // 普通类： 继承:Any() toString Windows实现打印了   com.derry.s5.ResponseResultBean1@266474c2com.yzy.xxkotlindemo.ResponseResultBean1@f6f08b9
        println(ResponseResultBean1("loginSuccess", 200, "登录成功的数据..."))

        // 数据类：继承 : Any() 默认重写了 父类的 toString  打印子类的toString详情信息  ResponseResultBean2(msg=loginSuccess, code=200, data=登录成功的数据...)
        println(ResponseResultBean2("loginSuccess", 200, "登录成功的数据..."))


        // 前面我们学习  == 值的比较 相当于java的equals      ===引用 对象 比较
        // 推理 两个 普通类 的值 是一样的，应该是true ，实际背后并不是这样的 false
        // Any父类的 equals 实现 （ResponseResultBean1对象引用 比较 ResponseResultBean1对象引用）
        // 这里是2个不同对象的引用的比较 当然不同
        println(
            ResponseResultBean1("loginSuccess", 200, "登录成功的数据...") ==
                    ResponseResultBean1("loginSuccess", 200, "登录成功的数据...")
        )  //false


        // Any父类的 equals 被 数据类 重写了 equals 会调用 子类的 equals函数（对值的比较）
        // 这里是2个数据类的 值的比较 当然一样了
        println(
            ResponseResultBean2("loginSuccess", 200, "登录成功的数据...") ==
                    ResponseResultBean2("loginSuccess", 200, "登录成功的数据...")
        ) //true


           // todo =========使用copy() 重写toStringt()===========================================================

         val p1 =  KtBase92("李元霸") // 调用次构造初始化对象
          println(p1) // 主构造被调用了 // 次构造被调用 //重写toString()方法:  name:李元霸, age:99, coreInfo:增加非常核心的内容信息


          //   默认的copy toString hashCode equals 等等...  只管输出主构造参数信息，不输出次构造的参数信息
           //  所以  如果你既要输出主构造参数信息  又要输出次构造的参数信息 就 必须重写 copy toString hashCode equals 函数
        // 注意事项：使用copy的时候，由于内部代码只处理主构造，所以必须考虑次构造的内容
        val newP2 = p1.copy("李连杰", 78)
        println(newP2) // 主构造被调用了   // 重写toString()方法:  name:李连杰, age:78, coreInfo:  //todo 这里次构造函数的无输出内容


        // todo ====================================================================
        val(name, age, sex) = Student1("李四", 89, '男')
        println("普通类  析构后:name:$name, age:$age, sex:$sex") // 普通类 结构后:name:李四, age:89, sex:男


        val(name1, age1, sex1) = Student2Data("李四", 89, '男')
        println("数据类 析构后:name:$name1, age:$age1, sex:$sex1") //  数据类 结构后:name:李四, age:89, sex:男


        val(_, age2, _) = Student1("李四", 89, '男')
        println("数据类 析构后: age2:$age2") //   数据类 结构后: age2:89




    }




}



// 普通类
class ResponseResultBean1(var msg: String, var code: Int, var data: String) : Any()
// set get 构造函数



// 数据类 -- 一般是用于 JavaBean的形式下，用数据类
data class ResponseResultBean2(var msg: String, var code: Int, var data: String) : Any()
// set get 构造函数 解构操作 copy toString hashCode equals  数据类 生成 更丰富





data class KtBase92 (var name: String, var age: Int) // 主构造
{

    var coreInfo : String = ""
    init {
        println("主构造被调用了")
    }

    // 次构造
     constructor(name:String):this(name,age=99){
        println("次构造被调用")
        coreInfo = "增加非常核心的内容信息"

     }

    // 重写toString() 不仅输出主构造函数的参数信息 还输出 次构造函数的参数信息
    override fun toString(): String {
        return "重写toString()方法:  name:$name, age:$age, coreInfo:$coreInfo"
    }


    /* 生成的toString 为什么只有两个参数？
   答：默认生成的toString 或者 hashCode equals 等等... 只管输出主构造信息，不管次构造的信息
    public String toString() {
      return "KtBase92(name=" + this.name + ", age=" + this.age + ")";
    }
 */


}


// 普通类
class Student1(var name: String , var age: Int, var sex: Char) {

    // 注意事项：component0 顺序必须是 component1 component2 component3 和成员一一对应，顺序下来的
    operator fun component1() = name
    operator fun component2() = age
    operator fun component3() = sex
}


// 数据类
data class Student2Data(var name: String , var age: Int, var sex: Char)




