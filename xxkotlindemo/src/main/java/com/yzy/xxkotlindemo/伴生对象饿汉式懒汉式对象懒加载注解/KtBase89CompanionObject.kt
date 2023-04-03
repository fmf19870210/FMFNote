package com.yzy.xxkotlindemo.伴生对象饿汉式懒汉式对象懒加载注解

/**
 * Create by Fangmingfei on 2023-03-18 下午 5:29
 * Describe ： 伴生对象类
 */
open class KtBase89CompanionObject {

    val info1 = "非伴生对象里的字段:DerryINfo2"
    fun showAction1() {
        "调用函数showAction1()"
    }




    // 伴生对象
    //凡是在伴生对象里的字段 和函数方法 都可以直接类调用 类似java的静态类直接调用静态字段和静态函数
    //加了注解的可以直接在java类中被调用
     companion object{
        val info2 = "DerryINfo"
        fun showAction2() {"调用伴生对象里的函数:showAction()"}

        @JvmField
        val info3=  "黄石公园"
        @JvmStatic
       fun showAction3() {"调用伴生对象里的函数:showAction2()"}



        @JvmOverloads // 原理：编译器环节  专门重载一个函数，专门给 Java掉用
        fun toast( name : String, sex : Char = 'M') {
            println("name:$name, sex:$sex")
        }



    }


/**
 companion object {} 背后的逻辑

 private static final String name = "Derry"; // // name 私有的 私有的
 private static final String info = "DerryINfo"; // info 私有的
 public static final KtBase89.Companion Companion = new KtBase89.Companion(xxx);


 public static final class Companion {

 @NotNull
 public final String getInfo() {
 return KtBase89.info; // 类调用静态字段
 }

 @NotNull
 public final String getName() {
 return KtBase89.name;
 }


 public final void showInfo() {
 String var1 = "显示:" + ((KtBase89.Companion)this).getInfo();
 boolean var2 = false;
 System.out.println(var1);
 }

 private Companion() {}





 }

 */



}