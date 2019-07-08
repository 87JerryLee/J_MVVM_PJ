package com.jerrylee.mvvm

import android.app.Application
import com.jerrylee.lib.widget.stateview.core.LoadState

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 11:40
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    private fun initLoadState(){
        LoadState.Builder().build()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}
