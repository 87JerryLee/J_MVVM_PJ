package com.jerrylee.lib.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jerrylee.lib.widget.stateview.core.LoadManager
import me.yokeyword.fragmentation.SupportFragment

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 15:42
 */
abstract class BaseFragment : SupportFragment() {
    open val TAG: String by lazy { javaClass.simpleName }

    /**
     * @return
     */
    abstract val layoutResId: Int

    protected var loadManager: LoadManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadManager = LoadManager.Builder()
                .setViewParams(this)
                .setListener{onStateRefresh()}
                .build()
        initConfig(savedInstanceState)
        initUI(view, savedInstanceState)
        initData(savedInstanceState)
    }

    /**
     * 初始化UI
     */
    abstract fun initUI(view: View, savedInstanceState: Bundle?)

    protected open fun initData(savedInstanceState: Bundle?) {}

    protected open fun initConfig(savedInstanceState: Bundle?) {}

    protected open fun onStateRefresh(){}

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     *
     *
     * false 事件向上传递
     */
    override fun onBackPressedSupport(): Boolean {
        if (fragmentManager?.backStackEntryCount!! > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop()
        } else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false
        }
        return true
    }



}
