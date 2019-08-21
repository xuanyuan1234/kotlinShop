package com.xdw.baselibrary.injection.module

import android.app.Activity
import dagger.Module
import dagger.Provides

/**
 *  Created by zhou on 2019/8/9 23:40
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}