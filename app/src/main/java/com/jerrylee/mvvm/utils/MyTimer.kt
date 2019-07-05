package com.jerrylee.mvvm.utils

import android.os.Handler

import java.util.Timer
import java.util.TimerTask

/**
 * Description:定时器
 * Author: jerry Lee
 * Date: 2016/4/5 16:37
 */
class MyTimer(val delayTime: Long = 0,val repeatTime: Long = 2000, private val EVENTID: Int //消息ID
              , private val handler: Handler? //处理定时器消息
) {
    private val mTimer: Timer? by lazy { Timer(true) }
    private var mTimerTask: TimerTask? = null

    var isStart: Boolean = false
        private set

    /**
     * 开始定时器
     */
    @Synchronized
    fun startTimer() {
        if (isStart) {
            return
        }
        if (mTimerTask == null) {
            mTimerTask = object : TimerTask() {
                override fun run() {
                    handler?.sendEmptyMessage(EVENTID)
                }
            }
        }
        mTimer?.schedule(mTimerTask, delayTime, repeatTime)
        isStart = true
    }

    /**
     * 停止定时器
     */
    @Synchronized
    fun stopTimer() {
        if (!isStart) {
            return
        }
        mTimerTask?.cancel()
        mTimerTask = null
        isStart = false
    }

    /**
     * 释放空间
     */
    fun release() {
        mTimerTask?.cancel()
        mTimerTask = null
        mTimer?.cancel()
    }
}
