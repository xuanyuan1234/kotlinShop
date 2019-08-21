package com.xdw.baselibrary.rx

import com.xdw.baselibrary.common.ResultCode
import com.xdw.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 *  Created by zhou on 2019/8/11 15:23
 */
class BaseFuncBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}