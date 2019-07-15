package com.jerrylee.lib.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jerrylee.lib.widget.stateview.core.LoadManager

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 16:50
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var loadManager: LoadManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView(savedInstanceState)
        setContentView(getLayoutResId())
        val contentView:View? = findViewById(getContentId())
        contentView?.let {
            loadManager = LoadManager.Builder()
                    .setViewParams(findViewById(getContentId()))
                    .setListener{onStateRefresh()}
                    .build()
        }
        initConfig(savedInstanceState)
        initUI(savedInstanceState)
        initData(savedInstanceState)
    }

    /**
     * 初始化UI
     */
    abstract fun initUI(savedInstanceState: Bundle?)

    protected abstract fun getLayoutResId():Int

    /**
     * 获取需要显示loadingView的视图id
     */
    protected open fun getContentId():Int{
        return -1
    }

    protected open fun initData(savedInstanceState: Bundle?) {}

    protected open fun initConfig(savedInstanceState: Bundle?) {}

    protected open fun beforeSetContentView(savedInstanceState: Bundle?) {}

    protected open fun onStateRefresh(){}
}