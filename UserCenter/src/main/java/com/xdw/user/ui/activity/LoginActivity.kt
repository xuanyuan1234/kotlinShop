package com.xdw.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xdw.baselibrary.ext.enable
import com.xdw.baselibrary.ext.onClick
import com.xdw.baselibrary.ui.activity.BaseMvpActivity
import com.xdw.user.R
import com.xdw.user.data.protocol.UserInfo
import com.xdw.user.injection.component.DaggerUserComponent
import com.xdw.user.injection.module.UserModule
import com.xdw.user.presenter.LoginPresenter
import com.xdw.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * 登录界面
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        startActivity<UserInfoActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    /**
     * 初始化
     */
    private fun initView() {
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)

        mForgetPwdTv.onClick(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }
            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
                mPwdEt.text.isNullOrEmpty().not()
    }
}
