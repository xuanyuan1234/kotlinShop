package com.xdw.user.presenter

import com.xdw.baselibrary.ext.execute
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.rx.BaseSubscriber
import com.xdw.user.data.protocol.UserInfo
import com.xdw.user.presenter.view.LoginView
import com.xdw.user.service.impl.UserServiceImpl
import javax.inject.Inject

open class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    /**
     * 业务逻辑
     */
    fun login(mobile: String, pwd: String, pushId: String) {

        if (!checkNetWork()) {
            println("网络不可用")
            return
        }

        mView.showLoading()

        userService.login(mobile, pwd, pushId)
            .execute(object : BaseSubscriber<UserInfo>(mView) {
                override fun onNext(t: UserInfo) {
                    mView.onLoginResult(t)
                }
            }, lifecycleProvider)
    }
}