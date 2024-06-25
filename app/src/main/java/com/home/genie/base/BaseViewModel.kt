package com.home.genie.base


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.onroad.user.webservice.Retrofit
import com.home.genie.util.ApiInterface


abstract class BaseViewModel : ViewModel() {
    val throwable = MutableLiveData<Throwable?>()
    val success = MutableLiveData<Any>()

    val api : ApiInterface by lazy {
        Retrofit.createBaseApiService()
    }

    fun onResponseError(it: Throwable?) {
        throwable.postValue(it)
    }

}