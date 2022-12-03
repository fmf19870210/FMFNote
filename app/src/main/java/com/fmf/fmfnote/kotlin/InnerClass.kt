package net.println.kotlin.chapter4.innerclass

/**
 * Created by benny on 4/4/17.
 */
open class Outter{
    val a: Int = 0

    inner class Inner{
        val a: Int = 5

        fun hello(){
            println(this@Outter.a)
        }
    }

}

interface OnClickListener{
    fun onClick()
}

class View{
    var onClickListener: OnClickListener? = null
}

fun main(args: Array<String>) {
    val inner = Outter().Inner()

    val view = View()
    view.onClickListener = object : Outter(), OnClickListener{
        override fun onClick() {

        }
    }
}