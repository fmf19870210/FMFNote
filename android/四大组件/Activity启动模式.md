关于Activity的任务栈的介绍:
            Activity的任务栈 “后进先出” 的栈结构,每按一次Back键，就有一个Activity出栈









Android启动提供了四种启动方式：
                  
      
 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/android/%E5%9B%9B%E5%A4%A7%E7%BB%84%E4%BB%B6/activity_1.png)
 ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/android/%E5%9B%9B%E5%A4%A7%E7%BB%84%E4%BB%B6/activity_2.png)
![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/android/%E5%9B%9B%E5%A4%A7%E7%BB%84%E4%BB%B6/activity_3.png)


            
                一:标准模式（Standard）：每次都会创建一个新的activity对象入栈。 会造成重复浪费资源
                二:栈顶复用模式（SingleTop）:当你入栈一个新的acvity对象时,如果栈顶正好有这个acvity对象时可以直接复用,如果栈顶没有或者 在栈底,那么就只有重新创建一个新的对象放在栈顶了
                         @Override
						protected void onNewIntent(Intent intent) {
						    super.onNewIntent(intent);
						}

                   例如:点击通知栏的消息通知,启动进入到一个新的Activity界面,此Activity用singleTop，否则每次点击短信都会新建一个Activity。

				 三:栈内复用模式（SingleTask）: 一个栈内只有一个该Activity实例.
					当你入栈一个新的acvity对象时,如果栈中没有这个对象,那么就创建一个新的acvity对象入栈,如果栈中有这个acvity对象, 移除掉其的之上的其他的activity对象,直接将其拉到栈顶复用.
				
						<activity android:name=".Activity1"
							android:launchMode="singleTask"
							android:taskAffinity="com.yc.task"
							android:label="@string/app_name">
						</activity>
										

                   例如:主界面HomeActivity界面设置为SingleTask,不管我们打开了多少个Activity，只要我们再次回到主界面，都应该使用将主界面Activity上所有的Activity移除的方式来让主界面Activity处于栈顶，而不是往栈顶新加一个主界面Activity的实例， 通过这种方式能够保证退出应用时所有的Activity都能报销毁。



                四:单例模式（SingleInstance）：直接创建一个新的栈,单独存放这个activty对象。
              
                
  ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/android/%E5%9B%9B%E5%A4%A7%E7%BB%84%E4%BB%B6/singleInstance.png)             
         
 






标记位属性

				标记位属性 	             
				FLAG_ACTIVITY_SINGLE_TOP 	        指定启动模式为栈顶复用模式（SingleTop）
				FLAG_ACTIVITY_NEW_TASK 	            指定启动模式为栈内复用模式（SingleTask）
				FLAG_ACTIVITY_CLEAR_TOP 	       具有此标记位的Activity，当它启动时，在同一个任务栈中所有位于它上面的Activity都要出栈。                         									SingleTask模式默认具有此标记效果
				FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS 	具有该标记的Activity不会出现在历史Activity的列表中，即无法通过历史列表回到该

 




启动模式的设置方式:
             1.在AndroidMainifest的Activity配置进行设置        
              <activity

				android:launchMode="启动模式"
				//属性
				//standard：标准模式
				//singleTop：栈顶复用模式
				//singleTask：栈内复用模式
				//singleInstance：单例模式
				//如不设置，Activity的启动模式默认为**标准模式（standard）**
				</activity>  
              2.通过Intent设置标志位
				Intent inten = new Intent (ActivityA.this,ActivityB.class);
				intent,addFlags(Intent,FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);












blog：

https://www.jianshu.com/p/399e83d02e33


