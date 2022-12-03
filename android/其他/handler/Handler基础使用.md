




一:为什么要用 Handler消息传递机制

      在实际开发中可能会存在多个线程并发操作UI控件,导致UI线程不安全,    
       所以需要使用handler使将工作线程需操作UI的消息 传递 到主线程，使得主线程可根据工作线程的需求 更新UI，从而避免线程操作不安全的问题

二:为什么只有通过Handler 机制才能实现更新UI呢？
      
  最根本的就是为了解决多线程并发更新UI的问题。
  所以anddroid 给我们提供了一套更新UI的机制,我们遵循这个机制就行了,
 





三:andler机制的工作流程主要包括4个步骤：
 ![](https://upload-images.jianshu.io/upload_images/944365-a6a41fa7961184e2.png?imageMogr2/auto-orient/strip|imageView2/2/w/749)

![](https://upload-images.jianshu.io/upload_images/944365-c18fc8b78d4ec73c.png)

![](https://upload-images.jianshu.io/upload_images/944365-6cf14c6dc05cbf66.png?imageMogr2/auto-orient/strip|imageView2/2)
    

![](https://upload-images.jianshu.io/upload_images/944365-b649e05ecbf437c8.png)


![](https://upload-images.jianshu.io/upload_images/944365-494e0b26a2724087.png?imageMogr2/auto-orient/strip|imageView2/2)

![](https://upload-images.jianshu.io/upload_images/944365-c86c852fa0a64d5b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200)




三:Handler机制的核心类/原理 
Handler、 处理器 类   用来发送消息，处理消息 
Message、  消息实体对象，handler通过sendMsg将实体放到消息队列里面 

Looper  : 消息轮询器，死循环,  不断的从MeaagaeQueue消息队列里取出消息, 如果有消息就取出,交给handler处理；
          如果没有消息就阻塞。


 
Message Queue、  存放/移除 消息的队列 
 

     1.创建循环器对象（Looper） & 消息队列对象（MessageQueue）核心类:
     在主线程里会 自动生成  Looper对象，不需手动生成；
     而子线程的Looper对象则需手动通过Looper.prepare()创建，
     同时也会生成其对应的消息队列对象 MessageQueue对象。
     生成Looper & MessageQueue对象后，则会自动进入消息循环：Looper.loop（）

      
      2.Looper类的loop()消息循环：
         loop():从消息队列中获取消息、分发消息到Handler;消息循环（通过for循环）;
         通过next()方法：取出消息队列里的消息；若取出的消息为空(没有数据)，则线程阻塞/等待；
         通过dispatchMessage(msg)方法把消息数据分发给对应的Handler实例对象；
          在dispatchMessage(msg)方法里有个 handleMessage(msg)方法,我们通过实现handleMessage(Message msg)方法来实现消息数据的处理。
 
                 
     3.创建消息对象 Message msg = Message.obtain() ,使用obtain（）则是直接从池内获取     
     4.在子线程发送消息到消息队列中 mHandler.sendMessage(msg);
        追踪源码:sendMessage(Message msg)--->sendMessageAtTime(msg,delayMillis)--->enqueueMessage(queue, msg, uptimeMillis)-->
                  enqueueMessage(msg, uptimeMillis)：将消息Message   根据时间 放入到消息队列MessageQueue中.
                                                     采用单链表实现,提高插入消息、删除消息的效率
  


四:线程（Thread）、循环器（Looper）、处理者（Handler）之间的对应关系如下：

        1个线程（Thread）只能绑定 1个循环器（Looper），但可以有多个处理者（Handler）
        1个循环器（Looper） 可绑定多个处理者（Handler） 



   ![](https://upload-images.jianshu.io/upload_images/944365-61b387c0e66ed8ee.png)




五:案例:

     
         ----------------------------------------------------------------------------
          1.使用 Handler.sendMessage（）案例:        
           public class MainActivity extends AppCompatActivity {
    
			    public TextView mTextView;
			    public Handler mHandler;
			
			    // 步骤1：（自定义）新创建Handler子类(继承Handler类) & 复写handleMessage（）方法
			    class Mhandler extends Handler {
			
			        // 通过复写handlerMessage() 从而确定更新UI的操作
			        @Override
			        public void handleMessage(Message msg) {
			            // 根据不同线程发送过来的消息，执行不同的UI操作
			            // 根据 Message对象的what属性 标识不同的消息
			            switch (msg.what) {
			                case 1:
			                    mTextView.setText("执行了线程1的UI操作");
			                    break;
			                case 2:
			                    mTextView.setText("执行了线程2的UI操作");
			                    break;
			            }
			        }
			    }
			
			    @Override
			    protected void onCreate(Bundle savedInstanceState) {
			        super.onCreate(savedInstanceState);
			        setContentView(R.layout.activity_main);
			
			        mTextView = (TextView) findViewById(R.id.show);
			
			        // 步骤2：在主线程中创建Handler实例
			        mHandler = new Mhandler();
			       
					        // 采用继承Thread类实现多线程演示
		        new Thread() {
		            @Override
		            public void run() {
		                try {
		                    Thread.sleep(3000);
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		
		                 // 步骤3：创建所需的消息对象
		                 Message msg = Message.obtain();
		                 msg.what = 1; // 消息标识
		                 msg.obj = "A"; // 消息内存存放
		
		                 // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
		                 mHandler.sendMessage(msg);
		            }
		        }.start();
		        // 步骤5：开启工作线程（同时启动了Handler）
		
		        // 此处用2个工作线程展示
		        new Thread() {
		            @Override
		            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通过sendMessage（）发送
                 // a. 定义要发送的消息
                 Message msg = Message.obtain();
                 msg.what = 2; //消息的标识
                 msg.obj = "B"; // 消息的存放
                 // b. 通过Handler发送消息到其绑定的消息队列
                 mHandler.sendMessage(msg);
				            }
				        }.start();
				
				    }
				}

       -------------------------------------------------------------------
         2.使用匿名内部类
            
             public class MainActivity extends AppCompatActivity {
    
				    public TextView mTextView;
				    public Handler mHandler;
				
				    @Override
				    protected void onCreate(Bundle savedInstanceState) {
				        super.onCreate(savedInstanceState);
				        setContentView(R.layout.activity_main);
				
				        mTextView = (TextView) findViewById(R.id.show);
				
				        // 步骤1：在主线程中 通过匿名内部类 创建Handler类对象
				        mHandler = new Handler(){
				            // 通过复写handlerMessage()从而确定更新UI的操作
				            @Override
				            public void handleMessage(Message msg) {
				                // 根据不同线程发送过来的消息，执行不同的UI操作
				                switch (msg.what) {
				                    case 1:
				                        mTextView.setText("执行了线程1的UI操作");
				                        break;
				                    case 2:
				                        mTextView.setText("执行了线程2的UI操作");
				                        break;
				                }
				            }
				        };
				        // 采用继承Thread类实现多线程演示
				        new Thread() {
				            @Override
				            public void run() {
				                try {
				                    Thread.sleep(3000);
				                } catch (InterruptedException e) {
				                    e.printStackTrace();
				                }
				                 // 步骤3：创建所需的消息对象
				                 Message msg = Message.obtain();
				                 msg.what = 1; // 消息标识
				                 msg.obj = "A"; // 消息内存存放
				
				                 // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
				                 mHandler.sendMessage(msg);
				            }
				        }.start();
				        // 步骤5：开启工作线程（同时启动了Handler）
				
				        // 此处用2个工作线程展示
				        new Thread() {
				            @Override
				            public void run() {
				                try {
				                    Thread.sleep(6000);
				                } catch (InterruptedException e) {
				                    e.printStackTrace();
				                }
				                // 通过sendMessage（）发送
				                 // a. 定义要发送的消息
				                 Message msg = Message.obtain();
				                 msg.what = 2; //消息的标识
				                 msg.obj = "B"; // 消息的存放
				                 // b. 通过Handler发送消息到其绑定的消息队列
				                 mHandler.sendMessage(msg);
					            }
					        }.start();
					
					    }
					
					}


         ----------------------------------------------------------------
       3.使用 Handler.post（）

       public class MainActivity extends AppCompatActivity {
    
    public TextView mTextView;
    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.show);

        // 步骤1：在主线程中创建Handler实例
        mHandler = new Handler();

        // 步骤2：在工作线程中 发送消息到消息队列中 & 指定操作UI内容
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 通过psot（）发送，需传入1个Runnable对象
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // 指定操作UI内容
                        mTextView.setText("执行了线程1的UI操作");
                    }

                });
            }
        }.start();
        // 步骤3：开启工作线程（同时启动了Handler）

        // 此处用2个工作线程展示
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("执行了线程2的UI操作");
                    }

                });
            }
        }.start();

    }

}






























































 








































































          
				


 