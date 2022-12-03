
1.某个控件 居中(layout_centerInParent)
意思是把控件的上下左右约束在布局的上下左右，这样就能把控件放在布局的中间了

 app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"
app:layout_constraintTop_toTopOf="parent"



2.水平居中 约束(layout_centerHorizontal)

 app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"


3.垂直约束(layout_centerVertical)

app:layout_constraintTop_toTopOf="parent"
 app:layout_constraintBottom_toBottomOf="parent"




4.水平位置左右偏移多少

水平偏移  layout_constraintHorizontal_bias =(0~1)
 垂直偏移  layout_constraintVertical_bias =(0~1)

app:layout_constraintHorizontal_bias="0.3" 
app:layout_constraintVertical_bias="0.3"



参考项目:activity_invitetask_detail
5.关于0dp

 官方不推荐在ConstraintLayout中使用match_parent，可以设置 0dp (MATCH_CONSTRAINT) 配合约束代替match_parent，实际使用时 使用0dp 表示 match_constraint 即可

<android.support.constraint.ConstraintLayout 
    android:layout_width="match_parent"
    android:layout_height="match_parent">
 
<TextView
        android:id="@+id/TextView1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="50dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:visibility="visible" />

</android.support.constraint.ConstraintLayout>



宽度设为0dp，左右两边约束parent的左右两边 填充慢父布局宽度



6.WRAP_CONTENT 的小问题

如果你的控件宽或高是 wrap_content 并且控件长度过长时，就会超出屏幕范围,导致他的约束会失效，


 
  没有超过边界的例子布局
  
 ![](https://upload-images.jianshu.io/upload_images/2427349-2034548d5ec40892.png)
 

超过边界约束被破坏(字体超出屏幕之外,导致字体无法显示)

 ![](https://upload-images.jianshu.io/upload_images/2427349-17cb2431f660728b.png)



修复后的效果

![](https://upload-images.jianshu.io/upload_images/2427349-b2021549b7b6be1d.png)





 有问题的布局文件
<View
        android:id="@+id/a"
        android:layout_width="120dp"
        android:layout_height="50dp"
        android:background="@android:color/holo_red_light"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/b"
        android:layout_width="wrap_content"
        android:layout_height="50dp"
        android:background="@android:color/holo_blue_light"
        android:text="是事实是事实是事实是事实是事实"
        app:layout_constraintLeft_toRightOf="@+id/a"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/a" />



修复后的布局文件

app:layout_constrainedWidth="true"  约束宽
app:layout_constrainedHeight="true"  约束高

 






7.权重链

layout_constraintHorizontal_weight 水品方向权重 需要把宽度都设为0dp

  <TextView
        android:id="@+id/TextView1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/TextView2"
        app:layout_constraintHorizontal_weight="2" />
 
    <TextView
        android:id="@+id/TextView2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toRightOf="@+id/TextView1"
        app:layout_constraintRight_toLeftOf="@+id/TextView3"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_weight="3" />
 
    <TextView
        android:id="@+id/TextView3"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toRightOf="@+id/TextView2"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_weight="4" />



---------
<View
        android:id="@+id/a"
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:background="@android:color/holo_red_light"
        app:layout_constraintHorizontal_weight="1"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/b" />

<View
        android:id="@+id/b"
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:background="@android:color/holo_blue_light"
        app:layout_constraintHorizontal_weight="2"
        app:layout_constraintLeft_toRightOf="@+id/a"
        app:layout_constraintRight_toLeftOf="@+id/c" />

<View
        android:id="@+id/c"
        android:layout_width="0dp"
        android:layout_height="50dp"
        android:background="@android:color/holo_green_light"
        app:layout_constraintHorizontal_weight="2"
        app:layout_constraintLeft_toRightOf="@+id/b"
        app:layout_constraintRight_toRightOf="parent" />




8. 控件组
    Group 可以同时控制多个控件的显示与隐藏