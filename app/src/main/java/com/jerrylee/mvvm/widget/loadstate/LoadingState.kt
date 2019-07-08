package com.jerrylee.mvvm.widget.loadstate

import com.jerrylee.lib.widget.stateview.stateview.BaseStateControl
import com.jerrylee.mvvm.R

class LoadingState : BaseStateControl() {
    override fun onCreateView(): Int {
        return R.layout.common_loading_view
    }

    override fun isVisible(): Boolean {
        return super.isVisible()
    }

}
