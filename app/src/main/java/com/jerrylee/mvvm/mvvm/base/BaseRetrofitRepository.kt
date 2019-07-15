package com.jerrylee.mvvm.mvvm.base

import com.jerrylee.lib.mvvm.BaseRepository

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/07/11 17:43
 */
open class BaseRetrofitRepository<T>:BaseRepository{

    protected var apiService:T? = null

    constructor(){
        apiService = null
    }



}