package com.jerrylee.lib.mvvm

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 14:50
 */
open class BaseRepository {

    private var mCompositeDisposable: CompositeDisposable? = null

    /**
     * 每一次Retrofit网络请求都加入CompositeDisposable管理
     */
    protected fun addDisposable(disposable: Disposable) {
        //管理网络请求
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    fun clear() {
        mCompositeDisposable?.clear()
    }

}
