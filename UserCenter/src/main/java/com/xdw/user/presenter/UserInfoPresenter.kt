package com.xdw.user.presenter

import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.user.presenter.view.UserInfoView
import com.xdw.user.service.impl.UserServiceImpl
import javax.inject.Inject

open class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserServiceImpl

}