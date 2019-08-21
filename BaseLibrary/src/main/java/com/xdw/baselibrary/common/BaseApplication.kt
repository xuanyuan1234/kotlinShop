package com.xdw.baselibrary.common

import android.app.Application
import android.content.Context
import com.xdw.baselibrary.injection.component.AppComponent
import com.xdw.baselibrary.injection.component.DaggerAppComponent
import com.xdw.baselibrary.injection.module.AppModule

/**
 *  Created by zhou on 2019/8/9 23:35
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initAppInjection()

        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
        lateinit var context: Context
    }
}