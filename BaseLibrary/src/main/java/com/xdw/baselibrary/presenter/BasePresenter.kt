package com.xdw.baselibrary.presenter

import android.content.Context
import com.xdw.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import com.xdw.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 *
 */
open class BasePresenter<T : BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}