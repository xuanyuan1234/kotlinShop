package com.xdw.baselibrary.ui.fragment

import android.os.Bundle
import com.xdw.baselibrary.common.BaseApplication
import com.xdw.baselibrary.injection.component.ActivityComponent
import com.xdw.baselibrary.injection.component.DaggerActivityComponent
import com.xdw.baselibrary.injection.module.ActivityModule
import com.xdw.baselibrary.injection.module.LifecycleProviderModule
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *
 */
open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text: String) {
        mActivityComponent.activity().toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent =
            DaggerActivityComponent.builder().appComponent((activity!!.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(activity!!))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}