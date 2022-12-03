package com.fmf.fmfnote.kotlin

import com.fmf.fmfnote.kotlin.bean.Banner
import com.fmf.fmfnote.kotlin.bean.HotKey
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface WanAndroidApi {

    @GET("users/{login}")
    fun getUserCoroutine(@Path("login") login: String): Deferred<User>


/**
 * 重点关注API里的suspend关键字。suspend是挂起的意思，提醒开发者此方法为耗时方法。
 * */
    @GET("/banner/json")
    suspend fun banners(): WanAndroidRoot<List<Banner>>
    @GET("/hotkey/json")
    suspend fun hotKeys(): WanAndroidRoot<List<HotKey>>
}