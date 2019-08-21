package com.xdw.baselibrary.injection.component

import android.content.Context
import com.xdw.baselibrary.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 *  Created by zhou on 2019/8/9 23:34
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context
}