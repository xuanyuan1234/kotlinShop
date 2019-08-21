package com.xdw.user.presenter

import com.xdw.baselibrary.ext.execute
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.rx.BaseSubscriber
import com.xdw.user.presenter.view.ForgetPwdView
import com.xdw.user.service.impl.UserServiceImpl
import javax.inject.Inject

open class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserServiceImpl

    /**
     * 业务逻辑
     */
    fun forgetPwd(mobile: String, verityCode: String) {

        if (!checkNetWork()) {
            println("网络不可用")
            return
        }

        mView.showLoading()

        userService.forgetPwd(mobile, verityCode)
            .execute(object : BaseSubscriber<Boolean>(mView) {
                override fun onNext(t: Boolean) {
                    if (t)
                        mView.onForgetPwdResult("验证成功")
                }
            }, lifecycleProvider)
    }
}