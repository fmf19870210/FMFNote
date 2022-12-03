package com.fmf.fmfnote.kotlin

data class WanAndroidRoot<T>( val data: T,
                              val errorCode: Int,
                              val errorMsg: String)