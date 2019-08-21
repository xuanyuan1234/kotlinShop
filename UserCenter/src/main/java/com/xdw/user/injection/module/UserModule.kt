package com.xdw.user.injection.module

import com.xdw.user.service.UserService
import com.xdw.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 *  Created by zhou on 2019/8/9 23:07
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }
}