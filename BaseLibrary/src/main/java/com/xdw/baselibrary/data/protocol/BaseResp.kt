package com.xdw.baselibrary.data.protocol

/**
 *  Created by zhou on 2019/8/9 0:14
 */
class BaseResp<T>(val status: Int, val message: String, val data: T) {

}