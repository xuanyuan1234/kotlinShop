package com.xdw.user.ui.activity

import android.os.Bundle
import com.xdw.baselibrary.ext.enable
import com.xdw.baselibrary.ext.onClick
import com.xdw.baselibrary.ui.activity.BaseMvpActivity
import com.xdw.user.R
import com.xdw.user.injection.component.DaggerUserComponent
import com.xdw.user.injection.module.UserModule
import com.xdw.user.presenter.ResetPwdPresenter
import com.xdw.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * 忘记密码界面
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    /**
     * 初始化
     */
    private fun initView() {
        mConfirmBtn.enable(mPwdEt) { isBtnEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { isBtnEnable() }

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
                toast("密码不一致")
                return@onClick
            }

            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() and
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
