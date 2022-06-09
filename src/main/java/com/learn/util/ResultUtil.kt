package com.learn.util

import com.learn.Entity.Result
import com.learn.Entity.ResultEnum

object ResultUtil {
    /**成功且带数据 */
    /**成功但不带数据 */
    @JvmOverloads
    fun success(data: Any? = null): Result<*> {
        val result: Result<*> = Result<Any?>()
        result.code = ResultEnum.SUCCESS.code
        result.msg = ResultEnum.SUCCESS.msg
        result.setData(data)
        return result
    }

    /**失败 */
    fun error(code: Int?, msg: String?): Result<*> {
        val result: Result<*> = Result<Any?>()
        result.code = code
        result.msg = msg
        return result
    }
}