package com.jerrylee.lib.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 16:50
 */
abstract class BaseActivity : AppCompatActivity() {
    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContentView(savedInstanceState)
        setContentView(layoutResId)
        initConfig(savedInstanceState)
        initUI(savedInstanceState)
        initData(savedInstanceState)
    }

    /**
     * 初始化UI
     */
    abstract fun initUI(savedInstanceState: Bundle?)

    protected open fun initData(savedInstanceState: Bundle?) {}

    protected open fun initConfig(savedInstanceState: Bundle?) {}

    protected open fun beforeSetContentView(savedInstanceState: Bundle?) {}

}