package com.yzy.xxkotlindemo


/**
 *
 * 构造函数的初始化顺序
 * */
// 第一步：生成val sex: Char
class KtBase77(_name: String, val sex: Char) // 主构造
{
 
    // 第二步： 生成val mName  // 由于你是写在 init代码块前面，所以先生成你， 其实类成员 和 init代码块 是同时生成
    val mName = _name
    var number = 9
    init {
        val nameValue = _name // 第三步：生成nameValue细节
        println("init代码块打印:nameValue:$nameValue")
        number = number.times(9)
    }



    // 次构造 三个参数的  必须调用主构造
    constructor(name: String, sex: Char, age: Int) :this(name, sex) {
        // 第五步：生成次构造的细节 
        println("次构造 三个参数的, name:$name, sex:$sex, age:$age")
    }

    // 第四步
    val derry = "AAA"

    // 纠正网上优秀博客的错误： 类成员先初始生成   再init代码块初始生成  错误了
    // Derry正确说法：init代码块 和 类成员 是同时的，只不过你写在 init代码块前面 就是先生成你
} 

// TODO 77.Kotlin语言的构造函数初始化顺序学习
// 1.main 调用次构造 constructor(name sex age)
// 2.先调用主构造函数,初始化主构造函数的参数val sex: Char 
  
// 3.初始化参数var mName = _name
// 4.执行init { nameValue 打印 } 代码块
fun main() {
    // 调用次构造
    KtBase77("李元霸", '男', 88)  // 调用次构造
}
























