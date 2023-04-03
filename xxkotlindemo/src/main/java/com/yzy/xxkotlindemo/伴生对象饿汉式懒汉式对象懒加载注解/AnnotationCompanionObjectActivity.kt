package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.yzy.xxkotlindemo.R
import com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解.KtBase89CompanionObject

/**
 * Create by Fangmingfei on 2023-04-01 下午 9:19
 * Describe ： KtBase89CompanionObject
 *  一:    companion object 伴生对象的学习
 *  伴生对象的由来： 在KT中是没有Java的这种static静态，伴生很大程度上和Java的这种static静态 差不多的
 *  无论 KtBase89CompanionObject()  构建对象多少次，我们的伴生对象，只有一次加载,伴生对象只会初始化一次
 *   凡是在伴生对象里的字段 和函数方法 都可以直接类调用
 *
 *
 *
 *二: 注解的学习  KtBase89CompanionObject
 *   因为项目 老功能用Java开发的   新功能使用kt开发
 *  java和kt 的交互 ,就需要kt提供一些注解 给Java给使用
 *  这样在java代码开发中就能调用kt 的类和功能函数方法(必须有注解)
 *
 *
 @file:JvmName("Stu")
必须写在 包名的外面 否则无法修改该kt的类名
修改我们的kt类的名字为Stu，让Java端调用更简洁

@JvmField 背后会剔除私有代码 成员
java代码就可以直接调用kt类中某个被@JvmField注解过的属性成员





@JvmOverloads
fun toast( name : String, sex : Char = 'M') {println("name:$name, sex:$sex")}

在kt类中:专门重载一个函数，专门给 Java调用
在Java类中:可以调用kt类类中被@JvmOverloads 注解的函数toast( name : String, sex : Char = 'M') 可以享用该函数的默认参数
 KtBase133Kt.toast("张三");



@JvmStatic



 */
class AnnotationCompanionObjectActivity   : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //todo 调用非伴生对象函数info1  需要创建KtBase89CompanionObject()对象开可以调用非伴生对象的字段和函数
        val p = KtBase89CompanionObject().info1
        val showInfo  = KtBase89CompanionObject().showAction1()


         //调用伴生对象里的非注解的字段和函数  直接调用
         KtBase89CompanionObject.info2
        KtBase89CompanionObject.showAction2()

        //调用伴生对象里的被注解的字段和函数  直接调用
        KtBase89CompanionObject.info3
      KtBase89CompanionObject.showAction3()
        KtBase89CompanionObject.toast(name = "方明飞")
    }


}