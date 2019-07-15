package com.jerrylee.lib.mvvm

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jerrylee.lib.widget.stateview.core.LoadManager

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 15:42
 */
abstract class BaseFragment : Fragment() {
    private lateinit var rootView: View

    protected var fragmentActivity:FragmentActivity?=null

    protected var loadManager: LoadManager? = null

    //懒加载
    private var isFirstVisible:Boolean = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutResId(), null, false)
        val contentLayout:View? = rootView.findViewById(getContentId())
        //是否需要添加loadingView
        contentLayout?.let {
            loadManager = LoadManager.Builder()
                    .setViewParams(it)
                    .setListener { onStateRefresh() }
                    .build()
        }
        initUI(rootView, savedInstanceState)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConfig(savedInstanceState)
        initData(savedInstanceState)

        val isVis = isHidden || userVisibleHint
        if (isVis && isFirstVisible) {
            lazyLoad()
            isFirstVisible = false
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    abstract fun getLayoutResId():Int

    /**
     * 获取需要显示loadingView的视图id
     */
    protected open fun getContentId() = -1

    /**
     * 初始化UI
     */
    abstract fun initUI(view: View, savedInstanceState: Bundle?)

    protected open fun initData(savedInstanceState: Bundle?) {}

    protected open fun initConfig(savedInstanceState: Bundle?) {}

    protected open fun onStateRefresh() {}

    /**
     * 当界面可见时的操作
     */
    protected fun onVisible() {
        if (isFirstVisible && isResumed) {
            lazyLoad()
            isFirstVisible = false
        }
    }

    /**
     * 数据懒加载
     */
    protected open fun lazyLoad() {

    }

    /**
     * 当界面不可见时的操作
     */
    protected open fun onInVisible() {

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        fragmentActivity = context as? FragmentActivity
    }

    override fun onDetach() {
        super.onDetach()
        fragmentActivity = null
    }





}
