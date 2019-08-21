package com.xdw.user.presenter

import com.xdw.baselibrary.ext.execute
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.rx.BaseSubscriber
import com.xdw.user.presenter.view.RegisterView
import com.xdw.user.service.impl.UserServiceImpl
import javax.inject.Inject

open class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    /**
     * 业务逻辑
     */
    fun register(mobile: String, pwd: String, verityCode: String) {

        if (!checkNetWork()) {
            println("网络不可用")
            return
        }

        mView.showLoading()

        userService.register(mobile, pwd, verityCode)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    if (t)
                        mView.onRegisterResult("注册成功")
                }
            }, lifecycleProvider)
    }
}