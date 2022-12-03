




一:RecyclerView和NestedScrollView嵌套使用
(注： NestedScrollView 与 ScrollView 一样，内部只能容纳一个子控件)

如果RecyclerView的高度定死(400dp) ,RecyclerView当作一个普通的控件放进去它就可以滑动，外面的NestedScrollView也可以滑动


如果RecyclerView的高度是wrap_content或者match_parent,RecyclerView会被完全展开，有多少条数据就会创建多少个item进行全部展示

,item控件复用也就失效



<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.NestedScrollView
xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:descendantFocusability="blocksDescendants"
        android:orientation="vertical">

        <!-- This is the Header -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:background="#888888"
            android:gravity="center"
            android:text="Header"
            android:textColor="#0000FF"
            android:textSize="30sp" />

        <android.support.v7.widget.RecyclerView
            android:id="@+id/rc"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

        <!-- This is the Footer -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:background="#888888"
            android:gravity="center"
            android:text="Footer"
            android:textColor="#FF0000"
            android:textSize="30sp" />

    </LinearLayout>

</android.support.v4.widget.NestedScrollView>









二: NestedScroll-嵌套滚动机制

要实现NestedScroll嵌套滑动机制，那么该怎么办，非常关键的几个东西是：


NestedScrollingParent

NestedScrollingChild

NestedScrollingParentHelper

NestedScrollingChildHelper


为了实现外部的滚动，控件需要实现NestedScrollingParent接口；为了能在内部滚动，控件需要实现NestedScrollingChild接口。

NestedScrollView实现了NestedScrollingParent接口，所以它可以套别人
NestedScrollView也实现了NestedScrollingChild接口，即它既可以套别人，也可以被套

RecyclerView实现了NestedScrollingChild接口，所以它能被别人套




三:NestedScrollView 和 RecycleView 嵌套时出现滑动卡顿的情况
//其中 recyclerView.setNestedScrollingEnabled(false); 设置可以在 xml 文件中间进行设置：android:nestedScrollingEnabled="false"

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setSmoothScrollbarEnabled(true);
    linearLayoutManager.setAutoMeasureEnabled(true);
    recyclerView.setHasFixedSize(true);
    recyclerView.setNestedScrollingEnabled(false);
    recyclerView.setLayoutManager(linearLayoutManager);




四:在 NestedScrollView 嵌套 RecycleView 时，可能会出现首次进入页面，页面的位置不在最顶部的问题。有可能是停在了 RecycleView 的部位。
在 NestedScrollView 唯一子布局中加入
android:descendantFocusability=“blocksDescendants”

android:descendantFocusability 有三个属性
beforeDescendants：优先于子控件获取焦点
afterDescendants：当子控件不需要焦点时，获取焦点
blocksDescendants：覆盖所有子控件获取焦点


五:在使用 NestedScrollView 作为父布局的时候，子布局的嵌套问题
使用 NestedScrollView 和 ScrollView 是一样的，其子布局是唯一的，不然在运行时会报错。因此在使用的时候要设置唯一的子布局进行展示。



六:NestedScrollView 嵌套 ViewPager 导致 ViewPager 显示不出来的问题

在 NestedScrollView 的布局中加入下面代码

android:fillViewport="true"




七:使用 ViewPager 嵌套 Fragment 时默认的我们使用 PagerAdapter／FragmentPagerAdapter／FragmentStatePagerAdapter 来管理 Fragment，这时会默认加载的 Fragment 的个数时系统默认的 DEFAULT_OFFSCREEN_PAGES = 1，即显示一个，预加载一个，如果我们想控制加载的 Fragment 的个数需要如何处理？

   可以通过设置 ViewPager 来进行管理
mViewPager.setOffscreenPageLimit(num);
mViewPager = new MyViewPager(getFragmentManager());
将 getFragmentManager() 替换为 getChildFragmentManager();




八:场景：当 ViewPager1 嵌套 Fragment0、Fragment0、Fragment1、Fragment0，在其中的 Fragment1 中又有一个 ViewPager2，在 ViewPager2 中嵌套 Fragment2，这时当我们滑倒 Fragment1 的时候，由于是第一次滑动到这个位置，Fragment2 正常显示，但是当我们滑动到其他位置，再返回到 Fragment1 时，就会出现 Fragment1 显示空白的问题，日志显示，正常加载。

 mViewPager = new MyViewPager(getFragmentManager());
 将 getFragmentManager() 替换为 getChildFragmentManager();




九:getFragmentManager()、getChildFragmentManager()、getSupportFragmentManager() 的区别：


getSupportFragmentManager(): 主要用于支持 3.0以下 android 系统
3.0以上系统可以直接调用 getFragmentManager() ,
getFragmentManager(): 所得到的是所在 fragment 的父容器的管理器
getChildFragmentManager(): 所得到的是在 fragment 里面子容器的管理器

当我们使用 Fragment 嵌套 Fragment 时，应该使用 getChildFragmentManager()




十:当点击上层布局结果下面的布局同时响应点击事件
在上层根布局中添加下面代码： android:clickable="true",这样可以使上层布局拦截点击事件，不会让下面的布局获取到。



十一:键盘问题

关于软键盘弹出的各个属性简介：
在manifest文件中可以设置Activity的android:windowSoftInputMode属性，这个属性值常见的设置如下：
android:windowSoftInputMode="stateAlwaysHidden|adjustPan"
那么这里值的含义列表如下：
【A】stateUnspecified：软键盘的状态并没有指定，系统将选择一个合适的状态或依赖于主题的设置
【B】stateUnchanged：当这个activity出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示
【C】stateHidden：用户选择activity时，软键盘总是被隐藏
【D】stateAlwaysHidden：当该Activity主窗口获取焦点时，软键盘也总是被隐藏的
【E】stateVisible：软键盘通常是可见的
【F】stateAlwaysVisible：用户选择activity时，软键盘总是显示的状态
【G】adjustUnspecified：默认设置，通常由系统自行决定是隐藏还是显示
【H】adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间
【I】adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分












