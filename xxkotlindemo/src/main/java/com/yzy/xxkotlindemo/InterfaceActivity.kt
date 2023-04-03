package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-22 下午 6:03
 * Describe ：接口的实现类的学习
 *
 */
class InterfaceActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val iusb1:IUSB = Mouse()
        println(iusb1.usbInsertDevice)
       println(iusb1.usbVersionInfo)
       println(iusb1.insertUBS())

        val iusb2:IUSB = KeyBoard()
        println(iusb2.usbInsertDevice)
        println(iusb2.usbVersionInfo)
        println(iusb2.insertUBS())
        iusb2.usbInsertDevice="AAA"
        println(iusb2.usbInsertDevice)




         val iusb3 = Mouse2()
           println(iusb3.usbVersionInfo)
           println(iusb3.usbInsertDevice)
            println(iusb3.insertUSB())
    }
}







/**
 *1.接口里面的所有成员 和 接口本身 都是 public open 的，所以不需要open，这个是接口的特殊
 *2.接口不能有主构造，反正就是没有构造
 *  3.实现类不仅仅要重写接口的函数，也要重写 接口的成员变量
 *4.接口实现类的代码区域，全部都要增加 override 关键字来修饰
 * */
interface IUSB {
    var usbVersionInfo: String // USB版本相关的信息
    var usbInsertDevice: String // USB插入的设备信息

    fun insertUBS() : String
}


// 鼠标UBS实现类
class Mouse(override var usbVersionInfo: String= "USB 3.0", override var usbInsertDevice: String= "鼠标接入了USB 3.0接口"):IUSB{
    override fun insertUBS(): String = "Mouse  $usbInsertDevice,$usbVersionInfo"
}




// 键盘USB实现类(没有())
class KeyBoard :IUSB{

    override var usbVersionInfo: String = "USB 3.1"
     // 下面的 set get 都会持有 field，现在是你没有给 usbVersionInfo 赋值， 意味着field是没法让set/get持有的
       get() =  field
       set(value) {field=value}
    override var usbInsertDevice: String= "键盘接入了UBS口"
        get(){
            println("获取field值 = $field")
            return  field
        }
        set(value) {
            field = value
            println("设置value值=$value")
        }

    override fun insertUBS(): String {
        return  "KeyBoard $usbInsertDevice,$usbVersionInfo "
    }



}




/**
 1.接口 var 也是不能给接口的成员赋值的 （但是有其他办法）
 2.任何类 接口 等等  val 代表只读的，是不可以在后面动态set赋值 （也有其他办法）
 */
interface  USB2{
    val usbVersionInfo:String  // USB版本相关的信息
      get() = "99"//(1..100).shuffled().last().toString()
      // set(value) {}  // val 不需要set赋值

    val usbInsertDevice: String // USB插入的设备信息
    get() = "高级设备接入USB"
    // val 不需要set赋值

    fun insertUSB():String
}



// 鼠标UBS2实现类
class Mouse2:USB2{

    override val usbVersionInfo: String
        get() = super.usbVersionInfo
    override val usbInsertDevice: String
        get() = super.usbInsertDevice

    override fun insertUSB(): String {
        return  "Mouse2 $usbVersionInfo, $usbInsertDevice"
    }

}



