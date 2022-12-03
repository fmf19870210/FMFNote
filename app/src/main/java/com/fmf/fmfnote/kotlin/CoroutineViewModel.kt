package com.fmf.fmfnote.kotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmf.fmfnote.kotlin.bean.Banner
import com.fmf.fmfnote.kotlin.bean.HotKey
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineViewModel : ViewModel() {

  val banners = MutableLiveData<List<Banner>>()
  val hotKeys = MutableLiveData<List<HotKey>>()
   val toastMsg = MutableLiveData<String>()

  fun  viewModelCoroutine(){
       viewModelScope.launch (Dispatchers.IO){
         //挂起子协程
          val result:WanAndroidRoot<List<Banner>> = RetrofitInstance.wanAndroidApi.banners()
            banners.postValue(result.data)
           log("网络请求banners数据在IO线程挂起协程 ")
      }
  }


 fun showToast(){
        viewModelScope.launch (Dispatchers.Main){
            toastMsg.postValue("显示你给锤子,王八犊子")
        }
    }


    fun viewModelSequenceRequest(){
       viewModelScope.launch(Dispatchers.IO) {
           val start = System.currentTimeMillis()
           // 先请求Banners
           val result = RetrofitInstance.wanAndroidApi.banners()
           banners.postValue(result.data)
           // 再请求热键，只要是顺序执行即可且上一次的请求结果已拿到即可满足我们的使用场景。
           val keys = RetrofitInstance.wanAndroidApi.hotKeys()
           hotKeys.postValue(keys.data)
       }
    }

}