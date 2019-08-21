package com.xdw.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 *  Created by zhou on 2019/8/9 23:40
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun provideLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}