package com.xmm_homework.data.extensions

import com.google.gson.Gson

internal fun <A> String.fromJson(type: Class<A>): A {
    return Gson().fromJson(this, type)
}

internal fun <A> A.toJson(): String? {
    return Gson().toJson(this)
}