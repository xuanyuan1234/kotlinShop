package com.xdw.baselibrary.rx

import com.xdw.baselibrary.common.ResultCode
import com.xdw.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 *  Created by zhou on 2019/8/11 15:23
 */
class BaseFunc<T> : Func1<BaseResp<T>, Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(t.data)
    }
}