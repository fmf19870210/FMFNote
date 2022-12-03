 1.Android程序是如何启动,Activity生命周期如何调用？

   Activity 的系统服务 ActivityMangerService 直接关联 ApplicationThread线程,
   ApplicationThread线程通过handler的机制  sendMessage方法进行发送操控Activty的各个生命周期,调用scherLauncherActivity


2.在Activity  onCreate当中我们的setContentView是如何将UI文件加载？




3.测量流程performMeasure
3.1.测量起点
    3.2.测量规格
    3.3.具体测量原理

测量结论:
就是通过执行测量performMeasure(childWidthMeasureSpec, childHeightMeasureSpec)测量方法,
一步一步追踪到具体的View的onMeasure()方法中，
而我们的控件根据自己需要的业务去进行重写onMeasure()测量方法,添加自己的测量业务,自定义控件.

打包规则结论:
View的测量流程中，通过makeMeasureSpec来打包保将size和mode打包成一个32位的int型数值(左两位代表模式，右30代表具体数值)，
在其他流程通过getMode得到模式  或getSize得到宽高。



4.布局流程performLayout
    2.1.布局起点
    2.2.布局定位
    2.3.具体布局

    由顶层的VIEW调用layout，然后公开onLayout出去让别人重写，施加自己的摆放业务，
    	FrameLayout为例，他当中自己去调用子View，然后将子View点算完给他，让他自己去摆放




5.在绘制流程中onMeasure测量为什么会执行2次(onMeasure为什么会2次测量)？

是在isViewVisible ＝ true(也就是view为显示状态下，这里会在此发起一次scheduleTraversals,就会再次调用执行一次onMeasure方法,再次进行测量。

 if (isViewVisible) {
            // Try again
            scheduleTraversals();
        } else if (mPendingTransitions != null && mPendingTransitions.size() > 0) {
            for (int i = 0; i < mPendingTransitions.size(); ++i) {
                mPendingTransitions.get(i).endChangingAnimations();
            }
            mPendingTransitions.clear();
        }


  如何解决2次测量的问题？
    我们在自定义控件时,在重写onMeasure()方法时,定义一个blooean 标签, 第一次true 第二次false




6.绘制流程performDraw









7.Fragment.onActivityResult() 与Activity.onActivityResult()的区别


在Fragment中 直接调用startActivityForResult时，fragment的OnActivityResult会执行， Activity的OnActvityResult方法执行  但他们的返回码 不一样

在Fragment中 当调用getActivity.startActivityForResult时，就会执行Activity的OnActvityResult方法。 fragment的OnActivityResult不执行





重写宿主Activity的onActivityResult()方法,这样就可以在fragment的onActivityResult()方法中接收返回值
在Activity的OnActivityResult中调用fragment的OnActivityResult方法


protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment*/
        Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
        f.onActivityResult(requestCode, resultCode, data);
    }






 startActivityForResult(intent, ConstantsType.REQUEST_COMMON_CODE)

  ShareAddFolderUploadFragment onActivityResult requestCode=1 resultCode=13117
 ShareAddFolderActivity onActivityResult requestCode=65537 resultCode=13117  //仓库

 ShareAddFolderUploadFragment onActivityResult requestCode=1 resultCode=0
  ShareAddFolderActivity onActivityResult requestCode=131073 resultCode=0 // 图片


ShareAddFolderUploadFragment onActivityResult requestCode=1 resultCode=0
  ShareAddFolderActivity onActivityResult requestCode=196609 resultCode=0// 视频


  ShareAddFolderUploadFragment onActivityResult requestCode=1 resultCode=0
 ShareAddFolderActivity onActivityResult requestCode=262145 resultCode=0 // 文件


----------------------------------------------
activity!!.startActivityForResult(intent, ConstantsType.REQUEST_COMMON_CODE)

ShareAddFolderActivity onActivityResult requestCode=1 resultCode=13117 // 仓库
ShareAddFolderActivity onActivityResult requestCode=1 resultCode=0  // 图片
ShareAddFolderActivity onActivityResult requestCode=1 resultCode=0   // 视频
ShareAddFolderActivity onActivityResult requestCode=1 resultCode=0   // 文件







