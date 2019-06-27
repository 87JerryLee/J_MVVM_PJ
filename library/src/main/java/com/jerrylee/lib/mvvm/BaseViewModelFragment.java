package com.jerrylee.lib.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.text.TextUtils;

import com.jerrylee.lib.utils.GenericsTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 15:27
 */
public abstract class BaseViewModelFragment<T extends BaseViewModel> extends BaseFragment {

    protected T mViewModel;

    private List<Object> eventKeys = new ArrayList<>();

    @Override
    protected void initConfig(Bundle savedInstanceState) {
        super.initConfig(savedInstanceState);
        ViewModelProviders.of(this).get((Class<T>) GenericsTool.getInstance(this,0));
        if (null != mViewModel) {
            dataObserver();
        }
    }

    protected void dataObserver() {
        //注册订阅
    }

    protected <T> MutableLiveData<T> registerSubscriber(Object eventKey, Class<T> tClass) {

        return registerSubscriber(eventKey, null, tClass);
    }

    protected <T> MutableLiveData<T> registerSubscriber(Object eventKey, String tag, Class<T> tClass) {
        String event;
        if (TextUtils.isEmpty(tag)) {
            event = (String) eventKey;
        } else {
            event = eventKey + tag;
        }
        eventKeys.add(event);
        return LiveDataBus.getDefault().subscribe(eventKey, tag, tClass);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //清除注册
        clearEvent();
    }

    private void clearEvent() {
        if (eventKeys != null && eventKeys.size() > 0) {
            for (int i = 0; i < eventKeys.size(); i++) {
                LiveDataBus.getDefault().clear(eventKeys.get(i));
            }
        }
    }
}
