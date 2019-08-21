package com.xdw.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xdw.baselibrary.ext.enable
import com.xdw.baselibrary.ext.onClick
import com.xdw.baselibrary.ui.activity.BaseMvpActivity
import com.xdw.user.R
import com.xdw.user.injection.component.DaggerUserComponent
import com.xdw.user.injection.module.UserModule
import com.xdw.user.presenter.ForgetPwdPresenter
import com.xdw.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    /**
     * 初始化
     */
    private fun initView() {
        mNextBtn.enable(mMobileEt) { isBtnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { isBtnEnable() }

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
                startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }
}
