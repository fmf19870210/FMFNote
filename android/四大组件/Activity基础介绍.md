


一:生命周期各个方法说明

  ![](https://raw.githubusercontent.com/fmf19870210/FMFNote/master/android/%E5%9B%9B%E5%A4%A7%E7%BB%84%E4%BB%B6/activity_4.png)


      1.onCreate()->onStart()(onRestart())->onResume()->onPause()->onStop()->onDestory()
         onCreate()：当 Activity 第一次创建时会被调用。这是生命周期的第一个方法。在这个方法中，可以做一些初始化工作，比如调用setContentView去加载界面布局资源，初始化Activity所需的数据。
         onStart(): 表示Activity正在被启动，即将开始，这时Activity还没有出现在前台(还在后台)，无法与用户交互。这个时候可以理解为Activity已经显示出来，但是我们还看不到。
         onRestart()：表示Activity正在被重新启动。当前Activity从不可见onstop 变为可见状态onstart时，onRestart就会被调用。
        onResume():表示Activity已经可见了，并且出现在前台并开始活动
        onPause(): Activity正在停止，但仍可见，
         onStop():表示Activity停止，不可见，位于后台。
          onDestory():表示Activity即将销毁。可以做一些回收工作和最终的资源回收。



二:A到B,然后在关闭B,回到A的生命周期介绍说明:
     
    1.A-->B-->A 开启B界面的生命周期： onCreate()->onStart()->onResume() 
                此时A界面(A处于不可见)的生命周期:   onPause()->onStop()      
                B-->A，此时A界面(A不可见-->可见)的生命周期:onRestart()->onStart()->onResume()

 
三: 当前A界面可见 按back键回退时A的生命周期 和 按Home键切换到桌面后A的生命周期 再次回到A界面时的生命周期  
             按back键回退时(finish当前了界面)，A页面生命周期：onPause()->onStop()->onDestory()
             按Home键切换到桌面后(A不可见了)A的生命周期 再次回到A界面时(A可见了)的生命周期： 
                            onPause()->onStop()(A不可见了)->onRestart()->onStart()->onResume()(A又可见了) 
        




四:横竖屏切换

       1.横竖屏切换的生命周期：onPause()->onSaveInstanceState()(保存当前界面的数据)-> onStop()->onDestroy()->onCreate()->onStart()->onRestoreInstanceState(获取数据)->onResume()



五:Activity三种运行状态

         1.Resumed（活动状态）(Running状态):Activity正在屏幕上显示，并且有用户焦点
        2.Paused（暂停状态）：Activity在屏幕上是可见的,没有焦点,不可交互。(一个对话框遮挡住了当前Activity,是当前Activity onpaused)
         3.Stopped（停止状态）：当Activity完全不可见时，此时Activity还在后台运行， 内存中保留Activity的状态，并不是完全销毁。







































