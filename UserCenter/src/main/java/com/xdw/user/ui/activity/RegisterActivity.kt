package com.xdw.user.ui.activity

import android.os.Bundle
import android.view.View
import com.xdw.baselibrary.common.AppManager
import com.xdw.baselibrary.ext.enable
import com.xdw.baselibrary.ext.onClick
import com.xdw.baselibrary.ui.activity.BaseMvpActivity
import com.xdw.user.R
import com.xdw.user.injection.component.DaggerUserComponent
import com.xdw.user.injection.module.UserModule
import com.xdw.user.presenter.RegisterPresenter
import com.xdw.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onRegisterResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    /**
     * 初始化
     */
    private fun initView() {
        mRegisterBtn.enable(mMobileEt) { isBtnEnable() }
        mRegisterBtn.enable(mVerifyCodeEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdConfirmEt) { isBtnEnable() }

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() and
               mVerifyCodeEt.text.isNullOrEmpty().not() and
               mPwdEt.text.isNullOrEmpty().not() and
               mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
