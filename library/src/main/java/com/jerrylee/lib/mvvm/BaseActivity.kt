package com.jerrylee.lib.mvvm

import android.os.Bundle
import android.view.View

import butterknife.ButterKnife
import butterknife.Unbinder
import me.yokeyword.fragmentation.SupportActivity

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 16:50
 */
abstract class BaseActivity : SupportActivity() {
    private var unBinder: Unbinder? = null

    protected abstract val layoutResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        unBinder = ButterKnife.bind(this)
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

    override fun onDestroy() {
        super.onDestroy()
        unBinder?.unbind()
    }
}
