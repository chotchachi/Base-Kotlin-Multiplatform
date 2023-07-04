package com.base.shared.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T, R> Flow<T>.mapTo(v: R): Flow<R> = transform {
    return@transform emit(v)
}

fun <T> Flow<T>.mapToUnit(): Flow<Unit> = transform {
    return@transform emit(Unit)
}