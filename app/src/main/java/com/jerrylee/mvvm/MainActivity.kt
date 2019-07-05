package com.jerrylee.mvvm

import android.os.Bundle
import android.view.View
import com.jerrylee.lib.mvvm.BaseViewModelActivity
import com.jerrylee.mvvm.mvvm.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<MainViewModel>() {
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun initUI(savedInstanceState: Bundle?) {
        textView.setOnClickListener{it.visibility = View.GONE}
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }

    override fun initConfig(savedInstanceState: Bundle?) {
        super.initConfig(savedInstanceState)
    }

}
