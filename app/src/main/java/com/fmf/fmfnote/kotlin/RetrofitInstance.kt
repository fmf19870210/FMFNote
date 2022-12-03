package com.fmf.fmfnote.kotlin

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }
    private  val  retrofitInstance:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }



     val wanAndroidApi:WanAndroidApi by lazy {
         retrofitInstance.create(WanAndroidApi::class.java)
     }


}