package com.xdw.user.service

import com.xdw.user.data.protocol.UserInfo
import rx.Observable

interface UserService {
    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>

    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>

    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
}