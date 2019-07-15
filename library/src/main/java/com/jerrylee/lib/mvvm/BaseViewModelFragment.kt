package com.jerrylee.lib.mvvm

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils

import com.jerrylee.lib.utils.GenericsTool

import java.util.ArrayList

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 15:27
 */
abstract class BaseViewModelFragment<T : BaseViewModel<*>> : BaseFragment() {

    var mViewModel: T? = null

    private val eventKeys = ArrayList<String>()

    override fun initConfig(savedInstanceState: Bundle?) {
        super.initConfig(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(GenericsTool.getInstance<Any>(this, 0) as Class<T>)
        if (null != mViewModel) {
            dataObserver()
        }
    }

    open fun dataObserver() {
        //注册订阅

    }

    protected fun <T> registerSubscriber(eventKey: String, tClass: Class<T>): MutableLiveData<T>? {
        eventKeys.add(eventKey)
        return LiveDataBus.default?.subscribe(eventKey, tClass)
    }

    open fun <T> registerSubscriber(eventKey: String, tag: String?, tClass: Class<T>): MutableLiveData<T>? {
        var newKey:String = eventKey.plus(tag?:"")
        return registerSubscriber(newKey,tClass)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //清除注册
        clearEvent()
    }

    private fun clearEvent() {
        if (eventKeys.size > 0) {
            for (i in eventKeys.indices) {
                LiveDataBus.default?.clear(eventKeys[i])
            }
        }
    }
}
