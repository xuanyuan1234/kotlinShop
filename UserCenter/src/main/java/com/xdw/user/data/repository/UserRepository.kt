package com.xdw.user.data.repository

import com.xdw.baselibrary.data.net.RetrofitFactory
import com.xdw.baselibrary.data.protocol.BaseResp
import com.xdw.user.data.api.UserApi
import com.xdw.user.data.protocol.*
import rx.Observable
import javax.inject.Inject

/**
 *  Created by zhou on 2019/8/9 0:21
 */
class UserRepository @Inject constructor() {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .register(RegisterReq(mobile, pwd, verifyCode))
    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .login(LoginReq(mobile, pwd, pushId))
    }

    fun forgetPwd(mobile: String, verifyCode: String): Observable<BaseResp<Boolean>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .forgetPwd(ForgetPwdReq(mobile, verifyCode))
    }

    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<Boolean>> {
        return RetrofitFactory.instance.create(UserApi::class.java)
            .resetPwd(ResetPwdReq(mobile, pwd))
    }
}