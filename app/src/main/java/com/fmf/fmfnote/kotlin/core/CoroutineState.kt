package com.fmf.fmfnote.kotlin.core

import java.util.concurrent.ThreadPoolExecutor

sealed class CoroutineState {

     private var disposableList:DisposableList =  DisposableList.Nil


     fun from(state: CoroutineState):CoroutineState{
          this.disposableList= state.disposableList
         return  this
     }


    fun with(disposable: Disposable  ):CoroutineState{
      this.disposableList=  DisposableList.Cons(disposable,this.disposableList)
        return this
    }

    fun without(disposable: Disposable): CoroutineState {
        this.disposableList = this.disposableList.remove(disposable)
        return this
    }








    fun clear(){
        this.disposableList = DisposableList.Nil
    }


    override fun toString(): String {
        return "CoroutineState.${this.javaClass.simpleName}"
    }





    class InComplete: CoroutineState()
    class Cancelling: CoroutineState()
    class Complete<T>(val value: T? = null, val exception: Throwable? = null): CoroutineState()
}