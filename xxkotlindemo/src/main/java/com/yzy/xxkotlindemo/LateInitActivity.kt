package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-15 下午 3:57
 * Describe ： lateinit  与 by lazy的学习
 */
class LateInitActivity   : AppCompatActivity() {
    val TAG = "MainActivity"


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/**
 *
 * by lazy   惰性加载   在调用使用的时候自动加载  该数据在创建的时候都已经初始化了
 *  ( isInitialized     ::responseResultInfo.isInitialized  判断当前变量是否初始化 不崩溃的)
 * lateinit   懒加载  在使用调用的时候才初始化赋值数据。  如果调用时没有初始化赋值数据就会崩溃。
 *
 *
 * */

// todo >>>>>>>>>>>>>>>>>>> 使用  lateinit   懒加载
        val p = KtBase78()
      //   p.showResponseResult() // 没有初始化会崩溃

        // 在调用使用它时,初始化赋值数据
         p.loadRequest()
         p.showResponseResult()   // responseResultInfo:服务器加载成功，恭喜你



        // todo >>>>>>>>>>>>>>>>>>> 使用惰性初始化 by lazy  普通方式
        val p2 = KtBase79()

         Thread.sleep(5000)

          Log.e(TAG,"即将开始使用 by lazy 惰性初始化加载 ")
        val  lazyResult  =   p2.databaseData2
         Log.e(TAG," by lazy 惰性初始化加载的数据= $lazyResult")

    }

}



class KtBase78 {

    /**
     *  lateinit   懒加载  在使用调用的时候才初始化赋值数据。  如果调用时没有初始化赋值数据就会崩溃。
     *    isInitialized     ::responseResultInfo.isInitialized  判断当前变量是否初始化 不崩溃的
     * */
    // lateinit val AAA; // val AAA 后面无法在修改了，我还怎么延时初始化？

    // lateinit 我等会儿后面调用你时在初始化你，我先定义再说，所以没有赋值  现在不初始化
    lateinit var responseResultInfo: String //var 可读可改


    // 模拟服务器加载
    fun loadRequest() { // 延时初始化，属于懒加载，用到你时,在给你加载初始化赋值
        responseResultInfo = "服务器加载成功，恭喜你"
    }



    fun showResponseResult() {
        // 由于你没有给他初始化赋值，所以只有用到它，就奔溃
        // if (responseResultInfo == null) println()
        //   Log.e("MainActivity","responseResultInfo:$responseResultInfo")

          //如果我忘了初始化 就调用了 怎么避免这个崩溃呢?   使用isInitialized判断当前变量是否初始化不崩溃的
          if(::responseResultInfo.isInitialized){
              Log.e("MainActivity","当前变量已经初始化赋值,responseResultInfo=$responseResultInfo")
          }else{
              Log.e("MainActivity","当前变量为null，没有初始化赋值,responseResultInfo=$responseResultInfo")
          }

    }

}



class KtBase79 {
/**
 *
 * by lazy   惰性加载   在调用使用的时候自动加载  该数据在创建的时候都已经初始化了
 *  ( isInitialized     ::responseResultInfo.isInitialized  判断当前变量是否初始化 不崩溃的)
 * lateinit   懒加载  在使用调用的时候才初始化赋值数据。  如果调用时没有初始化赋值数据就会崩溃。
 *
 * */

    // >>>>>>>>>>>>>>>>>>> 下面是 不使用惰性初始化 by lazy  普通方式(饿汉式 没有任何懒加载的特点)
    // val databaseData1 = readSQlServerDatabaseAction()


    // >>>>>>>>>>>>>>>>>>> 使用惰性初始化 by lazy  普通方式
    val databaseData2 by lazy(initializer = { readSQlServerDatabaseAction() })

    private fun readSQlServerDatabaseAction(): String {
        Log.e("MainActivity","开始读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","加载读取数据库数据中....")
        Log.e("MainActivity","结束读取数据库数据中....")
        return "database data load success ok."
    }

}




