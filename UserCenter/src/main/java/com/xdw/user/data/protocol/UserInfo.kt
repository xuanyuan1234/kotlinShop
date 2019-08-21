package com.xdw.user.data.protocol

/**
 *  Created by zhou on 2019/8/9 0:19
 *  用户实体类
 */
data class UserInfo(
    val id: String,
    val userIcon: String,
    val userName: String,
    val userGender: String,
    val userMobile: String,
    val userSign: String
)