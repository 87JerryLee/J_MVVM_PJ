package com.jerrylee.mvvm

import android.app.Application
import com.bumptech.glide.Glide
import com.jerrylee.lib.widget.stateview.core.LoadState
import com.jerrylee.mvvm.widget.loadstate.ErrorState
import com.jerrylee.mvvm.widget.loadstate.LoadingState

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 11:40
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLoadState()
    }

    private fun initLoadState(){
        LoadState.Builder().register(LoadingState()).register(ErrorState()).setDefaultStateView(LoadingState::class.java).build()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).onLowMemory()
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}
