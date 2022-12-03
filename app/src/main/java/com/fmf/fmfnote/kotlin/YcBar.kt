package com.fmf.fmfnote.kotlin

interface YcBar {

      var haha:String
      fun bar()
       fun foo() {
        //函数体是可选的
    }

}




class YcBarImpl: YcBar {
    override var haha: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun bar() {

    }
}