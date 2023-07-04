package com.base.kmm.android.extension.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun timer(delay: Long, unit: TimeUnit = TimeUnit.MILLISECONDS): Flow<Unit> {
    return flow {
        delay(unit.toMillis(abs(delay)))
        emit(Unit)
    }
}