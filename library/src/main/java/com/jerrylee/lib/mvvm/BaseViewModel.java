package com.jerrylee.lib.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.jerrylee.lib.utils.GenericsTool;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 14:49
 */
public class BaseViewModel<T extends BaseRepository> extends AndroidViewModel {
    public T mRepository;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        //实例化
        mRepository = GenericsTool.newInstance(this,0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //释放资源  如网络请求
        if (mRepository != null) {
            mRepository.clear();
        }
    }
}
