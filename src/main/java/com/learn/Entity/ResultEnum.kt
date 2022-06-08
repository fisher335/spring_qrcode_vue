package com.learn.Entity

enum class ResultEnum(val code: Int, val msg: String) {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(-1, "未知错误"), SUCCESS(10000, "成功"), USER_NOT_EXIST(1, "用户不存在"), USER_IS_EXISTS(
        2,
        "用户已存在"
    ),
    DATA_IS_NULL(3, "数据为空");

}