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

    private val eventKeys = ArrayList<Any>()

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

    protected fun <T> registerSubscriber(eventKey: Any, tClass: Class<T>): MutableLiveData<T> {

        return registerSubscriber(eventKey, null, tClass)
    }

    open fun <T> registerSubscriber(eventKey: Any, tag: String?, tClass: Class<T>): MutableLiveData<T> {
        val event: String
        if (TextUtils.isEmpty(tag)) {
            event = eventKey as String
        } else {
            event = eventKey.toString() + tag!!
        }
        eventKeys.add(event)
        return LiveDataBus.getDefault().subscribe(eventKey, tag, tClass)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //清除注册
        clearEvent()
    }

    private fun clearEvent() {
        if (eventKeys.size > 0) {
            for (i in eventKeys.indices) {
                LiveDataBus.getDefault().clear(eventKeys[i])
            }
        }
    }
}
