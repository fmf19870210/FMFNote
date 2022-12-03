package com.fmf.fmfnote.kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe

import com.fmf.fmfnote.R
import com.fmf.fmfnote.databinding.ActivityMainBinding
import com.fmf.fmfnote.kotlin.bean.Banner
import com.fmf.fmfnote.kotlin.bean.HotKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


class CoroutineActivity: AppCompatActivity() {


      private val  mainActivityBinding:ActivityMainBinding by lazy{
          ActivityMainBinding.inflate(layoutInflater,null,false)
      }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //   setContentView(R.layout.activity_main)
        setContentView(mainActivityBinding.root)

        /*  MainScope().launch {
            log(1)

            val job = GlobalScope.launch (start = CoroutineStart.DEFAULT) {
              log(2)
             }
            log(3)
          //  job.join()//只管运行的结果  1 3 2 4 如果没有join()方法  输出 1 3 4 2
            log(4)

        }*/


        /* MainScope().launch(start = CoroutineStart.DEFAULT) {
                log(1)
            val job= GlobalScope.launch(start = CoroutineStart.LAZY) {
                log(2)
                }
             log(3)
            //子协程启动模式是LAZY的话,job 必须要调用start方法,否则通过LAZY启动的子协程代码逻辑不执行  仅输出: 1 3  4
             //调用start方法, 子协程代码逻辑执行  输出: 1 3  4  2
             job.start()
            log(4)
        }*/

        /* MainScope().launch(start = CoroutineStart.DEFAULT) {
            log(1)
            val job= GlobalScope.launch(start = CoroutineStart.LAZY) {
                log(2)
            }
            log(3)
            //子协程启动模式是LAZY的话,job 必须要调用join方法,否则通过LAZY启动的子协程代码逻辑不执行  仅输出: 1 3  4
            //调用join方法,   要等待子协程代码逻辑(log(2))执行完毕后,才会执行下面的代码(log(4)),    因此输出的结果一定是： 1 3  2   4(注意与start()方法的区别)
            job.join()
            log(4)
        }*/


        /*  GlobalScope.launch (start = CoroutineStart.DEFAULT){
             val deferred = GlobalScope.async<Any> {
                   "方明飞"
                //  throw ArithmeticException("出异常了!!")
            }
            try {
                val value=   deferred.await()
                log("1. $value")  //1. 方明飞
            }catch (e:Exception){
                log("2. $e") // 2. java.lang.ArithmeticException: 出异常了
            }
        }*/


        /* GlobalScope.launch (start = CoroutineStart.DEFAULT){
              val deferred  =GlobalScope.async<Any> {
                   throw ArithmeticException("出异常了!!")
              }
             try {
                  deferred.join()
                 log("11. join()无返回结果值,") //输出:  11. join()无返回结果值,

             }  catch (e:Exception){
                 log("22. $e")
             }
         }*/

//==========================
        /*  val jobb =   GlobalScope.launch (start = CoroutineStart.DEFAULT){
       */
        /**
         * getUserCoroutine 返回的 Deferred 可以当做一个子协程，它应当遵循默认的作用域规则，在父作用域取消时其也要取消掉
         * 但现实却并不是这样：
         * 调用deferred.await() 的时候抛了个取消异常JobCancellationException，
         * 这主要是因为 await() 所在的协程已经被我们用 cancelAndJoin() 取消
         * *//*
        val deferred:Deferred<User>  =   gitHubServiceApi.getUserCoroutine("bennyhuo")

            deferred.invokeOnCompletion {

                log("retrofit -> invokeOnCompletion, $it, ${deferred.isCancelled}")

            }
        try {
              val userBean :User=      deferred.await()
            log("retrofit -> "+Gson().toJson(userBean)) //retrofit -> {"age":0,"name":"bennyhuo"}
        }catch (e:  Exception){
            log("retrofit ->await抛出的异常 $e")
        }


    }*/

        /*GlobalScope.launch {
        delay(50)
        log("retrofit ->网络延迟了50毫秒,返回数据 ")
        jobb.cancelAndJoin()
        log("retrofit ->jobb.cancelAndJoin()取消了网络请求 ")
    }*/


        //默认的创建 Flow数据对象
        /*val   intFlow =  flow<Any> {
                (1..5).forEach {
                    log(it)
                    emit(it)//新元素通过 emit 函数提供  使用 emit 重新生产新元素出
                    log(" emit "+emit(it))
                    delay(100)
                }
            }

        val myDispatcher = Executors.newSingleThreadExecutor { Thread(it, "MyThread").also { it.isDaemon = true } }.asCoroutineDispatcher()
          MainScope().launch {
              GlobalScope.launch(myDispatcher) {
                  intFlow.flowOn(Dispatchers.IO) //flowOn 与 RX的 subscribeOn(Schedulers.io()) 对应
                      //collect 所在的协程自然就是观察者,它想运行在什么调度器上它自己指定即可，非常容易区分。
                      //collect 所在协程的调度器则与 RX的observeOn(Schedulers.from(myExecutor)) 指定的调度器对应。
                   // collect  是 消费 Flow 的数据末端操作符
                      .collect {  //collect 所在的协程就是观察者
                          log(" collect1 "+it)
                      }
                  //Flow产生的数据 可以被重复消费
                  intFlow.collect {  log(" collect2 "+it) }
              }.join()
          }*/


        //使用channelFlow 函数来创建 Flow对象,生成元素数据时可以切换调度器

        /* val channelflow=   channelFlow<Any> {
                  send("方")
           //切换调度器 在IO里生产数据
              withContext(Dispatchers.IO) {
                  send("明")
                  send("飞")
              }
          }*/

        /* GlobalScope.launch{
            channelflow.collect {
                log( it)  //输出 方明飞
            }
        }*/


        // 通过集合框架来创建 Flow对象：
        /* val listflow= listOf("hello",1,"2",3.0).asFlow<Any>()
        MainScope().launch {
           listflow.collect {
               log( it)  //输出 "hello",1,"2",3.0
           }
       }*/


        // 使用 collectLatest 解决背压问题
        //collectLatest不会直接用新数据覆盖老数据，而是每一个都会被处理，只不过如果前一个还没被处理完后一个就来了的话，处理前一个数据的逻辑就会被取消。
        /* MainScope().launch {
         flow<Any> {
             List(100){
                 emit(it)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        )
             }
         }.collectLatest() {
               log( it)
             }
     }*/


        // 主线程内启动一个协程
        //启动模式:start: CoroutineStart = CoroutineStart.DEFAULT,
        MainScope().launch(Dispatchers.Main) {
        // 切换到IO线程
            withContext(Dispatchers.IO)  {
              delay(1000)

              log( "在子线程")
          }
             // 切回主线程
            log( "在主线程")
        }

        lifecycleScope.launch(Dispatchers.Main) {
            // 切换到IO线程
            withContext(Dispatchers.IO)  {
                delay(1000)
                 log( "在子线程")
            }
            // 切回主线程
            log( "在主线程")
        }



          // 主线程内启动一个协程
        // lifecycleScope.launch()默认就是在主线程启动协程；
        //lifecycleScope内的协程在Lifecycle为destroyed状态时会自动取消。
        //lifecycleScope 是我使用的 Lifecycle 的协程扩展库当中的，你可以替换成自定义的协程作用域。
       /* lifecycleScope.launch {
            //asFlow() 返回Flow<Int> 的对象, 相当于rx的被观察者Observable<T> 数据源
            (1..10).asFlow()
                // 切换到IO线程
                .flowOn(Dispatchers.IO) //flowOn   使用的参数是协程对应的调度器， 改变的是协程对应的线程。  类似RX的subscribeOn
                .catch { e -> //异常捕获,对应着 RxJava 中的 onError，

                }
                .onCompletion {//onCompletion  处理完成操作 对应RX的onComplete方法
                    log("数据消费完成")
                }
                .collect { num -> //collect相当于RX的观察者subscribe 订阅，消费数据的。collect对应的消费数据的线程是:lifecycleScope 的启动调度器 Dispatchers.Main,消费数据在主线程。
                    // 具体的消费处理
                    log(num)

                }
        }*/


        coroutineViewModel.banners.observe(this,{bannerList:List<Banner>->
           var titleList:List<String?> =  bannerList.map { banner:Banner-> banner.title }
            var descList:List<String?> = bannerList.map { banner: Banner -> banner.desc }
             mainActivityBinding.tv1.text= titleList.toTypedArray().contentToString()
            mainActivityBinding.tv2.text= descList.toTypedArray().contentToString()
        }
        )
        //IO线程 里挂起协程请求数据
        coroutineViewModel.viewModelCoroutine()


       coroutineViewModel.toastMsg.observe(this) { str:String->
           Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
       }
        coroutineViewModel.showToast()


        coroutineViewModel.hotKeys.observe(this) { keyList:List<HotKey>->
            val content:List<String?> = keyList.map{hotKey:HotKey->hotKey.name}
            mainActivityBinding.tv3.text = content.toTypedArray().contentToString()
        }
        coroutineViewModel.viewModelSequenceRequest()

    }//todo

private val  coroutineViewModel:CoroutineViewModel by viewModels<CoroutineViewModel>()






    public interface Continuation<in T> {
        public val context: CoroutineContext
        public fun resume(value: T)
        public fun resumeWithException(exception: Throwable)
    }


        val gitHubServiceApi by lazy {

            val retrofit = retrofit2.Retrofit.Builder()

                .baseUrl("https://api.github.com")


                .addConverterFactory(GsonConverterFactory.create())
                //添加对 Deferred 的支持

                .addCallAdapterFactory(CoroutineCallAdapterFactory())

                .build()


            retrofit.create(WanAndroidApi::class.java)

        }


        fun main1(continuation: Continuation<Unit>): Any? {
            return println("hello 亲爱的")
        }


        fun toast(str: String) {
            Toast.makeText(this@CoroutineActivity, str, Toast.LENGTH_SHORT).show()
        }




}//todo