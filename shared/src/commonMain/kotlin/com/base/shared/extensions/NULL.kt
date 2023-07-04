package com.base.shared.extensions

object NULL {
    @Suppress("UNCHECKED_CAST")
    inline fun <T> unbox(v: Any?): T = if (this === v) null as T else v as T
}