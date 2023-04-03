package com.yzy.xxkotlindemo

import android.util.Log

/**
 * Create by Fangmingfei on 2023-03-18 下午 4:01
 * Describe ：
 */
object KtBase87 {

    /* object 对象类背后做了什么事情

    public static final KtBase87 INSTANCE;

    private KtBase87() {} // 主构造废除一样的效果

    public final void show() {
        String var1 = "我是show函数...";
        ...
        System.out.println(var1);
    }

    // 这个区域与java的 object 不同点：
    static {
        KtBase87 var0 = new KtBase87();
        INSTANCE = var0;
        String var1 = "KtBase91 init...";
        ...
        System.out.println(var0);
    }

 */


    init {
        Log.e( "MainActivity", "KtBase87 init...")
    }


    fun show() =   Log.e( "MainActivity","我是show函数...")


}