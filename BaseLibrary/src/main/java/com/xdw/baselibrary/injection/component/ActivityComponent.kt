package com.xdw.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.trello.rxlifecycle.LifecycleProvider
import com.xdw.baselibrary.injection.ActivityScope
import com.xdw.baselibrary.injection.module.ActivityModule
import com.xdw.baselibrary.injection.module.LifecycleProviderModule
import dagger.Component

/**
 *  Created by zhou on 2019/8/9 23:39
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class, LifecycleProviderModule::class])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}