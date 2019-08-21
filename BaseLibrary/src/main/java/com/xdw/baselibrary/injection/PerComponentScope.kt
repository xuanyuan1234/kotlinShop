package com.xdw.baselibrary.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 *  Created by zhou on 2019/8/9 23:45
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope