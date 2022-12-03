package com.fmf.fmfnote.kotlin.core

import com.fmf.fmfnote.kotlin.coroutines.Job
import com.fmf.fmfnote.kotlin.coroutines.OnCancel
import com.fmf.fmfnote.kotlin.coroutines.OnComplete
import java.util.concurrent.CancellationException
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

abstract class AbstractCoroutine<T>(override val context: CoroutineContext):Job,Continuation<T>  {
protected  val parentJob=context[Job]
  protected  val state = AtomicReference<CoroutineState>()
    override val isActive: Boolean
        get() = TODO("Not yet implemented")
    override val isCompleted: Boolean
        get() = TODO("Not yet implemented")

   /* override fun invokeOnCompletion(onComplete: OnComplete): Disposable {
        TODO("Not yet implemented")
    }

    override fun invokeOnCancel(onCancel: OnCancel): Disposable {
        TODO("Not yet implemented")
    }

    override fun remove(disposable: Disposable) {
        TODO("Not yet implemented")
    }*/

   /* override suspend fun join() {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }
*/


    override fun resumeWith(result: Result<T>) {
        val newState = state.updateAndGet{
                prevState ->
            when(prevState){
                is CoroutineState.Cancelling,
                is  CoroutineState.InComplete->{  CoroutineState.Complete(result.getOrNull(), result.exceptionOrNull()).from(prevState)}
                is CoroutineState.Complete<*>->{
                    throw IllegalStateException("Already completed!")
                }

            }

        }


       // newState.notifyCompletion(result)
        newState.clear()


    }

 private fun tryHandleException(e:Throwable):Boolean{
     return when(e){
         is CancellationException ->false
         else ->{(parentJob as? AbstractCoroutine<*>)?.handleChildException(e)?.takeIf {it}?:handleJobException(e)}
     }
 }


 protected  open fun  handleChildException(e: Throwable): Boolean{
     cancel()
     return tryHandleException(e)
 }


    protected open fun handleJobException(e: Throwable): Boolean {
        return false
    }


}