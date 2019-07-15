package com.jerrylee.lib.mvvm

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.text.TextUtils

import java.util.concurrent.ConcurrentHashMap

/**
 * Description:基于LiveData实现的事件管理
 * Author: jerry Lee
 * Date: 2019/06/27 16:16
 */
class LiveDataBus private constructor() {

    private val mLiveBus: ConcurrentHashMap<String, LiveBusData<Any>> = ConcurrentHashMap()

    fun <T> subscribe(eventKey: String): MutableLiveData<T> {
        return subscribe(eventKey, Any::class.java) as MutableLiveData<T>
    }

    fun <T> subscribe(eventKey: String,  tClass: Class<T>): MutableLiveData<T>{
        when(mLiveBus.contains(eventKey)){
            true -> {
                val liveBusData = mLiveBus[eventKey]
                liveBusData?.isFirstSubscribe = false
            }
            else -> {
                mLiveBus[eventKey]=LiveBusData(true)
            }

        }
        return mLiveBus[eventKey] as MutableLiveData<T>
    }

    fun <T> postEvent(eventKey: String, value: T): MutableLiveData<T> {
        val mutableLiveData = subscribe<T>(eventKey)
        mutableLiveData?.postValue(value)
        return mutableLiveData
    }

    class LiveBusData<T> internal constructor(var isFirstSubscribe: Boolean) : MutableLiveData<T>() {

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            super.observe(owner, ObserverWrapper(observer, isFirstSubscribe))
        }
    }

    private class ObserverWrapper<T>(private val observer: Observer<T>?, private var isChanged: Boolean) : Observer<T> {

        override fun onChanged(t: T?) {
            if (isChanged) {
                observer?.onChanged(t)
            } else {
                isChanged = true
            }
        }

    }

    fun clear(eventKey: String) {
        mLiveBus.remove(eventKey)
    }

    companion object {

        @Volatile
        private var instance: LiveDataBus? = null

        val default: LiveDataBus?
            get() {
                if (instance == null) {
                    synchronized(LiveDataBus::class.java) {
                        if (instance == null) {
                            instance = LiveDataBus()
                        }
                    }
                }
                return instance
            }
    }

}
