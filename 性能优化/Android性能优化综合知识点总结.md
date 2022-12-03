


https://juejin.im/post/5b50b017f265da0f7b2f649c



一、布局优化

  1.尽量减少不必要的嵌套,能用LinearLayout和FrameLayout，就不要用RelativeLayout，因为RelativeLayout控件相对比较复杂，测绘也想要耗时。

  2.使用include、merge和ViewStub
    include可以提高布局的复用性， 
    merge可以减少了自身的一层布局
    ViewStub 懒加载布局,需要用到布局的时候,再来加载到内存.

  3. 使用约束布局 ConstraintLayout 
    ConstraintLayout可以有效地解决布局嵌套过多的问题,ConstraintLayout使用约束的方式来指定各个控件的位置和关系的，




二、绘制优化,防止界面绘制渲染的卡顿

   1. 对onDraw方法的处理优化:
      onDraw方法中不要做耗时的任务:也不做过多的循环操作，特别是嵌套循环，虽然每次循环耗时很小，但是大量的循环势必霸占CPU的时间片，从而造成View的绘制过程不流畅。
      onDraw中不要创建新的局部对象: 因为onDraw()方法一般都会频繁大量调用，就意味着会产生大量的零时对象，不仅占用过多的内存，而且会导致系统更加频繁的GC，大大降低程序的执行速度和效率。


三、内存优化,防止内存泄漏


		 1、集合类泄漏
            集合类添加元素后，仍引用着集合元素对象，导致该集合中的元素对象无法被回收，从而导致内存泄露。
            解决方案:把mList清理掉，然后把它的引用也给释放掉。
            static List<Object> mList = new ArrayList<>();
			   for (int i = 0; i < 100; i++) {
			       Object obj = new Object();
			      mList.add(obj);
			       obj = null;
			    }

              
              mList.clear();
              mList = null;
    

  


		 2、单例/静态变量造成的内存泄漏
            单例模式具有其 静态特性，它的生命周期 等于应用程序的生命周期，正是因为这一点，往往很容易造成内存泄漏。
            



 
		 3、匿名内部类/非静态内部类
           3.1 非静态内部类他会持有他外部类的对象引用，非静态内部类的生命周期可能比外部类更长,就会导致泄漏
           
           解决方案: 把非静态内部类 改为 静态非静态内部类  前面加个static 即可
                 

              public class TestActivity extends Activity {
                     
                    private MyThread myThread = new MyThread();

				    @Override
				    protected void onCreate(Bundle savedInstanceState) {
				        super.onCreate(savedInstanceState);
				        setContentView(R.layout.activity_test);
				        new MyAscnyTask().execute(); // 开启一个异步线程程静态内部类
                        myThread.start(); //开启一个子线程静态内部类
				    }
				  


                  //改了这里 注意一下 static
				  private  static  class MyAscnyTask extends AsyncTask<Void, Integer, String>{
				        @Override
				        protected String doInBackground(Void... params) {
				            try {
				                Thread.sleep(100000);
				            } catch (InterruptedException e) {
				                e.printStackTrace();
				            }
				            return "";
				        }
				    }
				}
				

              //改了这里 注意一下 static
                private static class MyThread extends Thread {
				
				        @Override
				        public void run() {
				            super.run();
				
				            try {
				                sleep(100000);
				
				            } catch (InterruptedException e) {
				                e.printStackTrace();
				            }
				        }
				    }
				
				 

         3.2   静态内部类 使用 弱引用（WeakReference）来持有外部类的上下文
    
            public class TestActivity extends Activity {
                 private MyHandler myHandler = new MyHandler(TestActivity.this);

				 private static class MyHandler extends Handler {
				
				        WeakReference<TestActivity> weakReference;
				
				        MyHandler(TestActivity testActivity) {
				            this.weakReference = new WeakReference<TestActivity>(testActivity);
				
				        }
				
				        @Override
				        public void handleMessage(Message msg) {
				            super.handleMessage(msg);
				            weakReference.get().mText.setText("do someThing");
				
				        }
				    }

 
          }
 

		 
		    @Override
		    protected void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.activity_test);
		        mText = findViewById(R.id.mText);
		        myHandler.sendEmptyMessageDelayed(0, 100000);
		        
		    }

 
             //最后清空这些回调 
			    @Override
			    protected void onDestroy() {
			        super.onDestroy();
			        myHandler.removeCallbacksAndMessages(null);
			    }

 
		



      4、资源未关闭造成的内存泄漏
  
    
			    网络、文件等流忘记关闭
			    手动注册广播时，退出时忘记 unregisterReceiver()
			    Service 执行完后忘记 stopSelf()
			    EventBus 等观察者模式的框架忘记手动解除注册






四:启动速度优化

   1.冷启动（Cold start）(5秒)
     在冷启动开始时，系统有三个任务。这些任务是： 1、加载并启动应用程序 2、启动后立即显示应用程序的空白启动窗口 3、创建应用程序进程
        4、启动主线程 5、创建主Activity 6、加载布局 7、屏幕布局 8、执行初始绘制     


   2.热启动（Hot start）(1.5秒)
	   应用程序的热启动比冷启动要简单得多，开销也更低。在一个热启动中，系统都会把你的Activity带到前台。如果应用程序的Activity仍然驻留在内存中，那么应用程序可以避免重复对象初始化、布局加载和渲染。
	   热启动显示与冷启动方案相同的屏幕行为：系统进程显示空白屏幕，直到应用程序完成呈现活动。

  3.温启动（Warm start）(2秒)

 总结启动的优化:
	 1、利用提前展示出来的Window，快速展示出来一个界面，给用户快速反馈的体验； 
	    解决方案:使用Activity的windowBackground主题属性来为启动的Activity提供一个简单的drawable。




    2、避免在启动时做密集沉重的初始化（Heavy app initialization）；
	   解决方案:(1)比如像友盟，bugly这样的业务非必要的可以的异步加载。
              (2)比如地图，推送等，非第一时间需要的可以在主线程做延时启动。当程序已经启动起来之后，在进行初始化。       
 



    3、避免I/O操作、反序列化、网络操作、布局嵌套等。
 
 

五:包体优化
     (1)资源优化
       1. 开启资源压缩 shrinkResources true,自动删除无用的资源
       2. 可以在运行时动态绘制图像,使用<shape>以XML格式,可以减少APK的占用空间
       3. 对res资源的重复利用:
         比如一个三角按钮，点击前三角朝上代表收起的意思，点击后三角朝下，代表展开，一般情况下，我们会用两张图来切换，我们完全可以用旋转的形式去改变
		    <?xml version="1.0" encoding="utf-8"?>
		     <rotate xmlns:android="http://schemas.android.com/apk/res/android"
		    android:drawable="@drawable/ic_thumb_up"
		    android:pivotX="50%"
		    android:pivotY="50%"
		    android:fromDegrees="180" />

       4.使用图片压缩工具,压缩PNG和JPEG文件 您可以减少PNG文件的大小。(pngcrush，pngquant，或zopflipng)
       5.使用WebP文件格式 ,替代PNG或JPEG文件(使用Android Studio将现有的BMP，JPG，PNG或静态GIF图像转换为WebP格式);      

     (2) 代码混淆:压缩、优化、混淆等功能  
         minifyEnabled true

     (3) 插件化： 比如功能模块放在服务器上，按需下载，可以减少安装包大小。

 

六:耗电优化

应用为了保持应用进程长期在后台存活，使用各种不合理进程保活方案，导致用户电量严重耗损。
                方案:(1) 使用Battery Historian：是一款由 Google 提供的 Android 系统电量分析工具，直观地展示出手机的电量消耗过程，通过输入电量分析文件，显示消耗情况。
            
                (2)避免 Wake Lock 使用不当,及时释放Wake Lock:
                               Wake Lock是一种锁的机制,只要拿着这个锁,给CPU加了这个锁那系统就不会休眠了，这样做的目的是为了让我们程序一直运行。
                             
                               比如:及时通讯的心跳包会在熄屏不久后停止网络访问等问题,使用了Wake_Lock锁,就会让CPU一直唤醒,程序一直运行,心跳包就一直保持连接,但是却很耗电。 所以要慎用Wake Lock,及时释放Wake Lock
                             
                (3)使用 Job Scheduler 管理后台任务
                              JobScheduler是用来处理在某个时间点或者当满足某个特定的条件时执行一个任务的场景。
                               例如当设备接通电源或连接WiFi下启动下载更新的任务/进行上传的操作。这样可以在减少资源消耗的同时提升应用的效率。
                             


七:压缩Bitmap优化

        1.对图片质量进行压缩

             //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
              bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); 
          
            

						
						             public static Bitmap compressImage(Bitmap bitmap){  
						            ByteArrayOutputStream baos = new ByteArrayOutputStream();  
						            //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中  
						            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
						            int options = 100;  
						            //循环判断如果压缩后图片是否大于50kb,大于继续压缩  
						            while ( baos.toByteArray().length / 1024>50) {  
						                //清空baos  
						                baos.reset();  
						                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);  
						                options -= 10;//每次都减少10  
						            }  
						            //把压缩后的数据baos存放到ByteArrayInputStream中  
						            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());  
						            //把ByteArrayInputStream数据生成图片  
						            Bitmap newBitmap = BitmapFactory.decodeStream(isBm, null, null);  
						            return newBitmap;  
						        }  




   


八:线程优化

线程优化的思想是采用线程池，避免在程序中存在大量的Thread。线程池可以重用内部的线程，从而避免了现场的创建和销毁所带来的性能开销，同时线程池还能有效地控制线程池的最大并发数，避免大量的线程因互相抢占系统资源从而导致阻塞现象发生。


 解决方案: 使用线程池

优点： 1、减少在创建和销毁线程上所花的时间以及系统资源的开销。 
2、如不使用线程池，有可能造成系统创建大量线程而导致消耗完系统内存以及”过度切换”。
  
需要注意的是：
     1、如果线程池中的数量为达到核心线程的数量，则直接会启动一个核心线程来执行任务。
     2、如果线程池中的数量已经达到或超过核心线程的数量，则任务会被插入到任务队列中标等待执行。
     3、如果(2)中的任务无法插入到任务队列中，由于任务队列已满，这时候如果线程数量未达到线程池规定最大值，则会启动一个非核心线程来执行任务。
    4、如果(3)中线程数量已经达到线程池最大值，则会拒绝执行此任务，ThreadPoolExecutor会调用RejectedExecutionHandler的rejectedExecution方法通知调用者。

 
