package com.xdw.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.trello.rxlifecycle.LifecycleProvider
import com.xdw.baselibrary.data.protocol.BaseResp
import com.xdw.baselibrary.rx.BaseFunc
import com.xdw.baselibrary.rx.BaseFuncBoolean
import com.xdw.baselibrary.rx.BaseSubscriber
import com.xdw.baselibrary.widgets.DefaultTextWatcher
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 *  Created by zhou on 2019/8/8 23:28
 */

fun <T> Observable<T>.execute(
    subscriber: BaseSubscriber<T>,
    lifecycleProvider: LifecycleProvider<*>
) {
    this.observeOn(AndroidSchedulers.mainThread())
        .compose(lifecycleProvider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

fun View.onClick(listener: View.OnClickListener): View {
    setOnClickListener(listener)
    return this
}

fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}

fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}