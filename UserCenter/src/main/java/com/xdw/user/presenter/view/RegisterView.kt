package com.xdw.user.presenter.view

import com.xdw.baselibrary.presenter.view.BaseView

interface RegisterView : BaseView {

    fun onRegisterResult(result: String)
}