package com.jerrylee.lib.mvvm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

import com.jerrylee.lib.utils.GenericsTool

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 14:49
 */
open class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application) {
    val mRepository: T? by lazy { GenericsTool.newInstance<T>(this, 0) }

    override fun onCleared() {
        super.onCleared()
        //释放资源  如网络请求
        mRepository?.clear()
    }
}
