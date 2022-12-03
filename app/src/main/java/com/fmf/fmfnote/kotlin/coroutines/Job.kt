package com.fmf.fmfnote.kotlin.coroutines

import io.reactivex.disposables.Disposable
import kotlin.coroutines.CoroutineContext


typealias OnComplete = () -> Unit
typealias OnCancel = () -> Unit


interface Job:CoroutineContext.Element {


    companion object Key : CoroutineContext.Key<Job>

    override val key: CoroutineContext.Key<*> get() = Job
    val isActive: Boolean

    val isCompleted: Boolean

    fun invokeOnCompletion(onComplete: OnComplete): Disposable

    fun invokeOnCancel(onCancel: OnCancel): Disposable

    fun remove(disposable: Disposable)

    suspend fun join()

    fun cancel()
}