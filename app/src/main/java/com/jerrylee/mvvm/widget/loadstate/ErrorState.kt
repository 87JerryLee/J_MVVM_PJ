package com.jerrylee.mvvm.widget.loadstate

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.jerrylee.lib.widget.stateview.stateview.BaseStateControl
import com.jerrylee.mvvm.R


class ErrorState : BaseStateControl() {
    override fun onCreateView(): Int {
        return R.layout.common_empty_view
    }

    override fun onViewCreate(context: Context, view: View) {
        val errorDesc = view.findViewById<TextView>(R.id.tv_error_desc)
        val errorIcon = view.findViewById<ImageView>(R.id.iv_error_icon)
        if (view.tag == null)return
        when (view.tag) {
            StateType.COMMON_NO_NETWORK -> {
                errorDesc.text = "网络不给力～_~"
                errorIcon.setImageResource(R.mipmap.empty_network)
            }
            StateType.COMMON_ERROR -> {
                errorDesc.text = "服务器异常"
                errorIcon.setImageResource(R.mipmap.empty_server)
            }
            StateType.COMMON_NO_DATA -> {
                errorDesc.text = "没有数据～_~"
                errorIcon.setImageResource(R.mipmap.empty_server)
            }
            else -> {
                errorDesc.text = "服务器异常"
                errorIcon.setImageResource(R.mipmap.empty_server)
            }
        }
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return false
    }
}