package com.jerrylee.mvvm

import android.os.Bundle
import android.webkit.WebSettings
import com.jerrylee.lib.mvvm.BaseActivity
import kotlinx.android.synthetic.main.activity_web.*

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/07/12 17:12
 */
class WebActivity:BaseActivity(){
    override fun initUI(savedInstanceState: Bundle?) {
        var webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.loadUrl("http://global.sina.cn/szzx/article/20190712/0520ead658051000.html?from=singlemessage")
    }

    override fun getLayoutResId(): Int = R.layout.activity_web

}