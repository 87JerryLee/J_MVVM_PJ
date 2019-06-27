package com.jerrylee.lib.mvvm;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Author: jerry Lee
 * Date: 2019/06/27 14:50
 */
public class BaseRepository {

    private CompositeDisposable mCompositeDisposable;

    protected void addDisposable(Disposable disposable) {
        //管理网络请求
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void clear() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

}
