package com.fmf.fmfnote.kotlin


class Button  : Clickable {
    override val statusValue: String
        get() =  getvalue()

    private fun getvalue(): String {
      return "haha"
    }

    override fun click() {
        println("click")
    }

    override fun longClick() {
        super.longClick()
    }


    override fun doubleClick() {
        super.doubleClick()
    }

}






interface Clickable {
    val statusValue:String
    fun click()
    fun longClick() = println("longClicked")
    fun doubleClick(){
        println("doubleClick")
    }
}




