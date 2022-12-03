package com.fmf.fmfnote.kotlin

  abstract  class BaseClass {
      abstract  var  haha:String
      abstract fun fun1()
      fun foo() {
          print("haha")
      }
}



class Derived : BaseClass() {
    override var haha: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun fun1() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
