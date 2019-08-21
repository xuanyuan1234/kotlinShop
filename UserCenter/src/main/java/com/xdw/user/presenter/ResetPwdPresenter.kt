package com.xdw.user.presenter

import com.xdw.baselibrary.ext.execute
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.rx.BaseSubscriber
import com.xdw.user.presenter.view.ResetPwdView
import com.xdw.user.service.impl.UserServiceImpl
import javax.inject.Inject

open class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    /**
     * 业务逻辑
     */
    fun resetPwd(mobile: String, pwd: String) {

        if (!checkNetWork()) {
            println("网络不可用")
            return
        }

        mView.showLoading()

        userService.resetPwd(mobile, pwd)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    if (t)
                        mView.onResetPwdResult("重置密码成功")
                }
            }, lifecycleProvider)
    }
}