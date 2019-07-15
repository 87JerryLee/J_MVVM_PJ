package com.jerrylee.mvvm.widget.loadstate

import android.content.Context
import android.view.View
import com.jerrylee.lib.widget.stateview.stateview.BaseStateControl
import com.jerrylee.mvvm.R
import kotlinx.android.synthetic.main.common_loading_view.view.*

class LoadingState : BaseStateControl() {
    override fun onCreateView(): Int {
        return R.layout.common_loading_view
    }

    override fun onViewCreate(context: Context?, view: View?) {
        super.onViewCreate(context, view)
        view?.loading_shimmer?.setShimmerAnimationDuration(1000)
        view?.loading_shimmer?.startShimmerAnimation()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        //loading界面不出发OnReload
        return true
    }
}
