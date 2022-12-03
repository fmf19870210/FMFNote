package com.fmf.fmfnote.kotlin

import android.util.Log

/**
 * Create by Fangmingfei on 2022-11-14 下午 5:27
 * Describe ：
 */
class KotlinDemo  {

    companion object{
        var school:String="武汉大学"
        @JvmField
        var industry="IT"
         fun callStaticMethod1(){
            Log.e("","调用静态方法1")
         }

      @JvmStatic
       fun callStaticMethod2(){
          Log.e("","调用静态方法2")
       }
    }


    fun String.setValueCallback(block:()->Unit){
        block()
    }


    fun  setValueCallback2(block: () -> Unit,action:(Int)->Int){
        block()
        val action1= action(1)
        Log.e("","我收到处理的值了-计算的结果：$action1")
    }


}



