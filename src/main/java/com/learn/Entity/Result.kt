package com.learn.Entity

class Result<T> {
    var code: Int? = null
    var msg: String? = null
    var data: T? = null
        private set

    constructor() : super() {}
    constructor(code: Int?, msg: String?, data: T) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    fun setData(data: T) {
        this.data = data
    }

    override fun toString(): String {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}'
    }
}