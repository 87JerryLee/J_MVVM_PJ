package com.jerrylee.mvvm

import android.os.Bundle
import com.jerrylee.lib.mvvm.BaseViewModelActivity
import com.jerrylee.mvvm.mvvm.vm.MainViewModel
import com.jerrylee.mvvm.widget.loadstate.LoadingState

class MainActivity : BaseViewModelActivity<MainViewModel>() {

    override fun initUI(savedInstanceState: Bundle?) {
//        textView?.setOnClickListener{it.visibility = View.GONE}

        loadManager?.showStateView(LoadingState::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun getContentId(): Int {
        return R.id.content
    }

}
