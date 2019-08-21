package com.xdw.baselibrary.injection.module

import android.content.Context
import com.xdw.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *  Created by zhou on 2019/8/9 23:34
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }
}