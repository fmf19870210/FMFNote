package com.fmf.fmfnote.kotlin

class EventManager {

    interface OnEventListener {
        fun onEvent(event:Int)
    }


 fun addOnEventListener(onEventListener:OnEventListener) {

 }

 fun  removeOnEventListener(onEventListener:OnEventListener){}

}