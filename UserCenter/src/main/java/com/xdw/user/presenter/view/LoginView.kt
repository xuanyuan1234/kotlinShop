package com.xdw.user.presenter.view

import com.xdw.baselibrary.presenter.view.BaseView
import com.xdw.user.data.protocol.UserInfo

interface LoginView : BaseView {

    fun onLoginResult(result: UserInfo)
}