package com.jerrylee.mvvm.utils

import android.view.Gravity
import android.widget.Toast
import com.jerrylee.mvvm.MainApplication

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/07/08 17:02
 */
object ToastUtil {
    private var oldMsg:String? = null
    private var toast: Toast? = null
    private var oneTime:Long = 0
    private var twoTime:Long = 0

    fun showToast(s:String?) {
        if (s.isNullOrEmpty())return
        if (toast == null) {
            toast = Toast.makeText(MainApplication.instance, s, Toast.LENGTH_SHORT)
//            toast?.setGravity(Gravity.CENTER, 0, 0)
            toast?.show()
            oneTime = System.currentTimeMillis()
        } else {
            twoTime = System.currentTimeMillis()
            if (s == oldMsg) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast?.show()
                }
            } else {
                oldMsg = s
                toast?.setText(s)
//                toast?.setGravity(Gravity.CENTER, 0, 0)
                toast?.show()
            }
        }
        oneTime = twoTime
    }
}