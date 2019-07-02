package com.jerrylee.lib.retrofit

import org.reactivestreams.Publisher

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 17:04
 */
object RxSchedulers {

    fun <T> io_main(context: Int): ObservableTransformer<T, T> {
        return ObservableTransformer { tObservable -> tObservable.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }

    fun <T> io_main(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()) }
    }
}
