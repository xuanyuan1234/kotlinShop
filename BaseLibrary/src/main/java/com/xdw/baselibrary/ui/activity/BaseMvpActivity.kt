package com.xdw.baselibrary.ui.activity

import android.os.Bundle
import com.xdw.baselibrary.common.BaseApplication
import com.xdw.baselibrary.injection.component.ActivityComponent
import com.xdw.baselibrary.injection.component.DaggerActivityComponent
import com.xdw.baselibrary.injection.module.ActivityModule
import com.xdw.baselibrary.injection.module.LifecycleProviderModule
import com.xdw.baselibrary.presenter.BasePresenter
import com.xdw.baselibrary.presenter.view.BaseView
import com.xdw.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *
 */
abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text: String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()

        mLoadingDialog = ProgressLoading.create(this)
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent =
            DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}