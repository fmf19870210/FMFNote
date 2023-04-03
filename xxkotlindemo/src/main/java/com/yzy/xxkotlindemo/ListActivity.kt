package com.yzy.xxkotlindemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.io.File

/**
 * Create by Fangmingfei on 2023-03-11 下午 2:55
 * Describe ：List Set Map知识点的介绍
 *   数组知识点介绍
 *
 */
class ListActivity: AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val TAG="MainActivity"


        //todo===============================================================================================================================

        /**
         * List创建与元素获取
         *  list为了防止空指针异常，下标越界异常崩溃
         *  使用getOrElse(index: Int, defaultValue: (Int) -> T   getOrNull(index: Int)+ 空合并操作符
         *
         * */
        val list2 = listOf("Derry", "Zhangsan", "Lisi", "Wangwu")
        // 普通取值方式：    索引  内部是运算符重载 [] == get(index: Int)
//        Log.e(TAG,list2[4])  // 奔溃  java.lang.ArrayIndexOutOfBoundsException: 4

        // 我们写KT代码，一定不会再出现，空指针异常，下标越界异常
        // 防止奔溃取值方式： getOrElse getOrNull
        // getOrElse(index: Int, defaultValue: (Int) -> T
        Log.e(TAG,list2.getOrElse(3,defaultValue = {"越界"})) //Wangwu
        Log.e(TAG,list2.getOrElse(4,defaultValue = {"越界"})) // 越界
        Log.e(TAG,list2.getOrElse(4402,defaultValue = {"你越界了啊"})) // 你越界了啊

        // getOrNull(index: Int)+ 空合并操作符
        Log.e(TAG,list2.getOrNull(3)?:"角标越界") // Wangwu
        Log.e(TAG,list2.getOrNull(4)?:"角标越界") //角标越界
        Log.e(TAG,list2.getOrNull(111)?:"角标越界") //角标越界




        /** list的不可变集合  可变集合
         *    不可变集合: listOf(vararg elements: T): List<T> 不可以进行增删操作
         *    可变集合:  mutableListOf(vararg elements: T): MutableList<T> 可以进行增删操作
         *    不可变集合 to 可变集合: list.toMutableList()
         *    可变集合 to 不可变集合:   mutableList.toList()
         * */

        // 不可变集合,不可以进行增删操作
        val list1:List<Int> = listOf(123, 456, 789)
        // list1.add
        // list1.remove



        //可变的集合，可以进行增删操作
        val list22:MutableList<String> = mutableListOf<String>("Derry", "Zhangsna", "Wangwu")
        list22.add("Derry")
        list22.add("赵六")
        list22.remove("Wangwu")
        Log.e(TAG,list22.toString()) //[Derry, Zhangsna, Derry, 赵六]


        //不可变集合 to 可变集合
        val list3:MutableList<Int> = list1.toMutableList()
        //可变的集合，可以进行增删操作
        list3.add(789)
        list3.add(111)
        list3.remove(123)
        Log.e(TAG,list3.toString()) // [456, 789, 789, 111]

        // 可变集合 to 不可变集合
        val list5:List<String> = list22.toList()
        // list5.add
        // list5.remove


        /**
          1.  +=  -=  操作: (可操作性)集合可以直接 添加或者删除某个元素
          2.removeIf{}   遍历集合中的所有元素,如果遍历的当前元素的过滤条件为true  就删除当前这个元素it

     */
        //  +=  -=  操作
        val list : MutableList<String> = mutableListOf("Derry", "DerryAll", "DerryStr", "Zhangsan")
        list += "李四"
        list += "Derry" // [Derry, DerryAll, DerryStr, Zhangsan, 李四, Derry]
        list -= "Derry" // [DerryAll, DerryStr, Zhangsan, 李四, Derry]
        Log.e(TAG,list.toString()) //  [DerryAll, DerryStr, Zhangsan, 李四, Derry]


        // 2.removeIf{}   如果过滤条件为true  就删除调用者集合里的当前这个元素it
          list.removeIf {
              Log.e(TAG,"输出看看it是什么鬼it=$it") //it 就是集合中的所有元素
              it.contains("fangmingfei") } //   遍历集合中的所有元素,如果集合中的某个元素it  it.contains("fangmingfei")=true     就删除当前这个元素 it
              Log.e(TAG,list.toString())  // [DerryAll, DerryStr, Zhangsan, 李四, Derry]


        list.removeIf { it == "李四" } //  遍历集合中的所有元素,如果集合中的某个元素it  it == "李四"为true    就删除当前这个元素 it
        Log.e(TAG,list.toString()) // [DerryAll, DerryStr, Zhangsan, Derry]

          list.removeIf { it.contains("Derry") } //  遍历集合中的所有元素,如果集合中的某个元素it ,it.contains("Derry")=true    就删除当前这个元素 it
          Log.e(TAG,list.toString())    // [Zhangsan]





        /**
         *  List集合遍历学习
         *  第一种 遍历方式： for
         *  第二种 遍历方式： forEach(action: (T) -> Unit)
         *  第三种 遍历方式： forEachIndexed(action: (index: Int, T) -> Unit)
         * */
        val list4 = listOf(1, 2, 3, 4, 5, 6, 7)

           // 第一种 遍历方式： for
        for (i in list4) {
            Log.e(TAG,"$i")
        }

        // 第二种 遍历方式： forEach(action: (T) -> Unit)
        list4.forEach(action = {
            // it == 每一个元素
            Log.e(TAG,"元素:$it  ")
        })

        // 第三种 遍历方式： forEachIndexed(action: (index: Int, T) -> Unit)
        list4.forEachIndexed(action =  { index, i ->
            Log.e(TAG,"下标:$index, 元素:$i    ")
        })


   //todo===============================================================================================================================


       /**
        Set创建与元素获取
        1. set 定义  不会有重复元素
        2.普通方式elementAt(index: Int) 根据角标index获取元素值,但是会越界奔溃
        3.使用elementAtOrElse(index: Int, defaultValue: (Int) -> T)
        elementAtOrNull(index: Int) 方式,获取元素 ,防止越界奔溃异常
        */

       val set: Set<String> = setOf("lisi", "wangwu", "zhaoliu", "zhaoliu") // set集合不会出现重复元素的
        // set[0] 没有这样 [] 的功能  取Set集合元素

        Log.e(TAG,set.elementAt(0))
        Log.e(TAG,set.elementAt(1))
        Log.e(TAG,set.elementAt(2))
       // Log.e(TAG,set.elementAt(3)) [3] 奔溃 会越界
      //  Log.e(TAG,set.elementAt(4)) [3] 奔溃 会越界

        //   set 集合，尽量使用  此方式，防止越界奔溃异常
        // elementAtOrElse(index: Int, defaultValue: (Int) -> T)
        Log.e(TAG,set.elementAtOrElse(0){ "当前角标$it,越界了"}) // lisi
        Log.e(TAG,set.elementAtOrElse(100,defaultValue = { "当前角标$it,越界了"}))   // 当前角标100,越界了
        // elementAtOrNull(index: Int) + 空合并操作符  一起使用
        Log.e(TAG,set.elementAtOrNull(0)?:"角标越界") // lisi
        Log.e(TAG,set.elementAtOrNull(100)?:"角标越界") // 角标越界



        /**
         * 可变Set集合
         *  mutableSetOf(vararg elements: T): MutableSet<T>
         *    +=    即  add(element)  添加元素
         *   -=      即  remove(element) 移除元素
         * */

        val set1 : MutableSet<String> = mutableSetOf("李元霸", "李连杰")
        Log.e(TAG,set1.toString()) // [李元霸, 李连杰]
         set1+="李俊" // [李元霸, 李连杰, 李俊]
         set1+="李元霸" // [李元霸, 李连杰, 李俊] 不能添加重复元素
        set1-="李连杰" // [李元霸, 李俊]
        set1.add("刘军") // [李元霸, 李俊, 刘军]
        set1.remove("刘军") // [李元霸, 李俊]
        Log.e(TAG,set1.toString())


     /**  list集合去重的几种方式
      *  1. List 转换 Set    : list.toSet()    元素去重 结果变成了Set集合了
      *  2. List 转 Set 转 List 也能去重 结果还是List集合
      *    list.toSet().toList()
      *    list.toSet().toMutableList()
      *  3.   list.distinct() 即 toMutableSet().toList()  List集合快捷去重
      *
      * */

     val list6 : MutableList<String> = mutableListOf("Derry", "Derry", "Derry", "Leo", "Lance") // list 可以重复元素
        // List 转 Set 去重
      //  val set6: Set<String> = list6.toSet()
      //  Log.e(TAG,set6.toString())   // [Derry, Leo, Lance]

        // List 转 Set 转 List 也能去重
        //val list8 :  List<String> = list6.toSet().toList()
       // Log.e(TAG,list8.toString()) // [Derry, Leo, Lance]

     ////   val list9: MutableList<String> = list6.toSet().toMutableList()
      //  Log.e(TAG,list9.toString()) //  [Derry, Leo, Lance]

        // distinct() 即 toMutableSet().toList()  List集合快捷去重
           list6.distinct()



        /**  数组
         *   1. intArray[角标] 常规操作的角标越界会奔溃
         *  2.elementAtOrElse(index: Int, defaultValue: (Int) -> Int)   角标越界不崩溃
         *     elementAtOrNull(index: Int) + 空合并操作符 一起来用  角标越界不崩溃
         *  3. List集合转 数组   list.toXXXArray()
         *
          java数组            kt数组
        IntArray             intArrayOf
        DoubleArray          doubleArrayOf
        LongArray           longArrayOf
        ShortArray          shortArrayOf
        ByteArray             byteArrayOf
        FloatArray           floatArrayOf
        BooleanArray         booleanArrayOf
        Array<对象类型>    arrayOf(vararg elements: T): Array<T>        对象数组


          */

          //1. intArray[角标] 常规操作的角标越界会奔溃
        val intArray  :IntArray = intArrayOf(1, 2, 3, 4, 5)
        Log.e(TAG, intArray[0].toString())
        Log.e(TAG, intArray[1].toString())
        Log.e(TAG, intArray[2].toString())
        Log.e(TAG, intArray[3].toString())
        Log.e(TAG, intArray[4].toString())
        //Log.e(TAG, intArray[5].toString()) // 奔溃：角标越界会越界异常

        // 2.elementAtOrElse(index: Int, defaultValue: (Int) -> Int)   角标越界不崩溃
        Log.e(TAG, intArray.elementAtOrElse(0) {  -1 }.toString()) // 1
        Log.e(TAG, intArray.elementAtOrElse(100) { -1 }.toString()) //  -1
       //  elementAtOrNull(index: Int) + 空合并操作符 一起来用  角标越界不崩溃
        Log.e(TAG, (intArray.elementAtOrNull(1) ?:"你越界啦").toString() ) // 2
        Log.e(TAG, (intArray.elementAtOrNull(666) ?:"你越界啦").toString() ) // 你越界啦


         //List集合转 数组  list.toXXXArray()
        val charArray : CharArray = listOf('A', 'B', 'C').toCharArray()

         // 对象数组 arrayOf(vararg elements: T): Array<T>
        val objArray : Array<File> = arrayOf<File>(File("AAA"), File("BBB"), File("CCC"))
        Log.e(TAG,objArray.toString()) //  [Ljava.io.File;@85196ca



        //todo===============================================================================================================================
          /**   Map集合
           *  通过key 获取Value值
           *   方式一 map[key]或者map.get(key) 获取Value值 找不到会返回value=null，不会奔溃
           *   方式二 getOrDefault(key: K, defaultValue:   V) 获取Value值 没有找不到会返回默认值value-1
           *   方式三 getOrElse(key: K, defaultValue: () -> V) 获取Value值 没有找不到会返回默认值value=-1
           * */


        val mMap1 : Map<String, Double> = mapOf<String, Double>("Derry" to(534.4), "Kevin" to 454.5)
        val mMap2 = mapOf<String, Double>(Pair("Derry", 545.4), Pair("Kevin", 664.4))
        val mMap : Map<String, Int> = mapOf<String, Int>("Derry" to 123,"Kevin" to 654)

       //   方式一 map[key]或者map.get(key) 获取Value值 找不到会返回value=null，不会奔溃
        Log.e(TAG, mMap["Derry"].toString()) //123
        Log.e(TAG, mMap.get("Kevin").toString()) //654     // get 与 [] 完完全全等价的
        Log.e(TAG, mMap["XXX"].toString()) // null

       // 方式二 getOrDefault(key: K, defaultValue:   V) 获取Value值 没有找不到会返回默认值value-1
        Log.e(TAG, (mMap.getOrDefault("Derry",-1).toString())) //123
        Log.e(TAG, (mMap.getOrDefault("Derry2",-1).toString())) //-1

        // 方式三 getOrElse(key: K, defaultValue: () -> V) 获取Value值 没有找不到会返回默认值value=-1
        Log.e(TAG, mMap.getOrElse("Derry",defaultValue = {-1}).toString())  //123
        Log.e(TAG, mMap.getOrElse("Derry2") { -1 }.toString())  //-1

        // 方式四 getValue 与Java一样 会奔溃  尽量不要使用此方式
        Log.e(TAG, mMap.getValue("Derry").toString())
     //   Log.e(TAG, mMap.getValue("XXX").toString())



        /**
         * 遍历Map的几种方式
         * */

        val map3 : Map<String, Int> = mapOf(Pair("Derry", 123), Pair("Kevin", 456), "Leo" to 789)
        // 第一种方式: forEach(action: (Map.Entry<K, V>) -> Unit)
        map3.forEach {
            // it 内容 每一个元素 (K 和 V)  每一个元素 (K 和 V)  每一个元素 (K 和 V)
            // it 类型  Map.Entry<String, Int>
            Log.e(TAG,"key=${it.key},value=${it.value}") //   key=Derry,value=123  key=Kevin,value=456  key=Leo,value=789
        }

        // 第二种方式： forEach(BiConsumer<? super K, ? super V> action)
        map3.forEach {  key: String, value: Int ->
            // 把默认的it给覆盖了
            Log.e(TAG,"key:$key, value:$value") // key:Derry, value:123  key:Kevin, value:456   key:Leo, value:789
        }

        // 第三种方式：
         map3.forEach{(key : String, value : Int) ->
             Log.e(TAG,"key:$key, value:$value")
         }


       // 第四种方式：
        for (item in map3) {
            // item 内容 每一个元素 (K 和 V)  每一个元素 (K 和 V)  每一个元素 (K 和 V)
            Log.e(TAG,"输出item是个什么鬼item:   $item")
            Log.e(TAG,"key:${item.key} ,value:${item.value}")

        }



/**
 * 可变Map集合
   mutableMapOf(vararg pairs: Pair<K, V>): MutableMap<K, V>
   getOrPut(key: K, defaultValue: () -> V)  没有/有key 的情况的操作
 * */

val map : MutableMap<String, Int> = mutableMapOf(Pair("Derry", 123), "Kevin" to 456, Pair("Dee", 789))
        // 1.可变Map集合的增删操作
        //增
        map["CCC"] = 888  //  put(key, value) 和 [] 等价的
        map.put("DDD", 999) //{Derry=123, Kevin=456, Dee=789, CCC=888, DDD=999}

        //增
         //+= 即  put(pair.first, pair.second) 添加键值对元素
        map += "AAA" to(111) //A.to(that: B)
        map += "BBB" to 1234  // {Derry=123, Kevin=456, Dee=789, CCC=888, DDD=999, AAA=111, BBB=1234}

          //删
          //  -=  即 remove(key) 通过key 删除map的value值
        map -= "Kevin"  // {Derry=123, Dee=789, CCC=888, DDD=999, AAA=111, BBB=1234}

        Log.e(TAG,map.toString())



        //2. getOrPut(key: K, defaultValue: () -> V)  没有/有key 的情况

        //有key的情况
        val r2  =    map.getOrPut("Derry",defaultValue = {666}) //如果map集合里存在key="Derry",就直接获取返回对应的value=123值, 那么默认value=666就失效了
        Log.e(TAG, r2.toString()) // 123

      // 没有key的情况
        val r: Int = map.getOrPut("FFF") { 555 } // 如果整个map集合里面没有 key="FFF" 元素，我就帮你先添加("FFF",555)到map集合中去，然后再从map集合中获取
        Log.e(TAG,r.toString()) // 555
        Log.e(TAG, map["FFF"].toString()) //555
        Log.e(TAG,map.toString()) //{Derry=123, Dee=789, CCC=888, DDD=999, AAA=111, BBB=1234, FFF=555}



 //todo===============================================================================================================================



    }







}

























































