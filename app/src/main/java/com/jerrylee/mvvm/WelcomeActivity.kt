package com.jerrylee.mvvm

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.jerrylee.lib.mvvm.BaseActivity
import kotlinx.android.synthetic.main.activity_welcome.*

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/28 15:58
 */
class WelcomeActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_welcome
    }

    private var countDownTimer:CountDownTimer? = null
    private var isFinished:Boolean = false
    private var isCanceled:Boolean = false

    override fun initUI(savedInstanceState: Bundle?) {
        loadManager?.showSuccess()

        buSkip.setOnClickListener{
            //取消定时器
            countDownTimer?.cancel()
            countDownTimer?.onFinish()
        }

        countDownTimer = object : CountDownTimer(3000,500){
            override fun onFinish() {
                startMain()
                println("onFinish")
            }

            override fun onTick(millisUntilFinished: Long) {
                buSkip.text = resources.getString(R.string.skip_)+(millisUntilFinished/1000+1)+"s"
                println("onTick:$millisUntilFinished")
            }

        }
        countDownTimer?.start()
    }



    @Synchronized fun startMain(){
        if (isFinished || isCanceled) return
        startActivity(Intent(this@WelcomeActivity,WebActivity::class.java))
        finish()
        isFinished = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isCanceled = true
        countDownTimer?.cancel()
    }

}