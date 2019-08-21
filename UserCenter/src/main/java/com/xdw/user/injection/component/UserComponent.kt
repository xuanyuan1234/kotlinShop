package com.xdw.user.injection.component

import com.xdw.baselibrary.injection.PerComponentScope
import com.xdw.baselibrary.injection.component.ActivityComponent
import com.xdw.user.injection.module.UserModule
import com.xdw.user.ui.activity.*
import dagger.Component

/**
 *  Created by zhou on 2019/8/9 23:11
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface UserComponent {

    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}