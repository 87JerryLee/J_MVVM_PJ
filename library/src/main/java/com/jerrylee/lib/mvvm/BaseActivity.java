package com.jerrylee.lib.mvvm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 16:50
 */
public abstract class BaseActivity extends SupportActivity{
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        unbinder = ButterKnife.bind(this);
        initConfig(savedInstanceState);
        initUI(savedInstanceState);
        initData(savedInstanceState);
    }

    /**
     * 初始化UI
     */
    public abstract void initUI(@Nullable Bundle savedInstanceState);

    protected void initData(Bundle savedInstanceState){}

    protected void initConfig(Bundle savedInstanceState){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
        }
    }

    protected abstract int getLayoutResId();
}
