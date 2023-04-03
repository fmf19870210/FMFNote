package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * Create by Fangmingfei on 2023-03-20 下午 4:51
 * Describe ：
 */
class EnumActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //不能这样调用 会报错
      //  Limbs.show()
      //  Limbs().show()

         println(Limbs.LEFT_HAND.show()) // 四肢是:左手的长度是:88,拿刀切菜
        println(Limbs.RIGHT_HAND.show()) // 四肢是:右手的长度是:88,扶助案板
        println(Limbs.LEFT_FOOT.show())  // 四肢是:左脚的长度是:140,扭了不能走路
        println(Limbs.RIGHT_FOOT.show()) // 四肢是:右脚的长度是:140,右脚正常

        // 更新枚举值
        Limbs.LEFT_HAND.updateData(LimbsInfo("左手2",99))
        Limbs.RIGHT_HAND.updateData(LimbsInfo("左手2", 99))
        Limbs.LEFT_FOOT.updateData(LimbsInfo("左脚2", 199))
        Limbs.RIGHT_FOOT.updateData(LimbsInfo("右叫2", 199))
    }
}



// 四肢信息class，我就是为了方便toString打印

data class LimbsInfo(var limbsInfo:String,var length:Int){
    fun show() {
        println("${limbsInfo}的长度是:$length")
    }
}


//枚举其实也是一个class，为什么，就是为了 枚举可以有更丰富的功能
// 枚举类 的主构造函数入参参数 个数 必须和 实体的枚举的参数个数 保持一致 否则报错
enum class Limbs(private var  limbsInfo: LimbsInfo,var doSomeThing:String){//四肢
    LEFT_HAND(LimbsInfo("左手",88),"拿刀切菜"), // 左手
    RIGHT_HAND(LimbsInfo("右手",88),"扶助案板"),  // 右手
    LEFT_FOOT(LimbsInfo("左脚", 140),"扭了不能走路"), // 左脚
    RIGHT_FOOT(LimbsInfo("右脚", 140),"右脚正常"); // 右脚 // 结束枚举值

    //  WEEK枚举 这个时候 再定义单调的 枚举值，就报错了，必须所有枚举值，保持一致的效果
   // 星期一,
    //星期二,
    //星期三,
   // 星期四,
    //星期五,
   // 星期六,
   // 星期日

    // 输出枚举的信息
    fun show() = "四肢是:${limbsInfo.limbsInfo}的长度是:${limbsInfo.length},$doSomeThing "


    fun updateData(updateLimbsInfo: LimbsInfo){
      println("更新前的数据信息是:${this.limbsInfo}")
     this.limbsInfo.limbsInfo= updateLimbsInfo.limbsInfo
     this.limbsInfo.length = updateLimbsInfo.length
        println("更新后的数据是:${this.limbsInfo}")

    }


 }




























