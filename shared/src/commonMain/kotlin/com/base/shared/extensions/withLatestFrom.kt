package com.base.shared.extensions

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun <A, B, R> Flow<A>.withLatestFrom(
    other: Flow<B>,
    transform: suspend (A, B) -> R
): Flow<R> {
    return flow {
        coroutineScope {
            val otherValues = Channel<Any>(Channel.CONFLATED)
            launch(start = CoroutineStart.UNDISPATCHED) {
                other.collect {
                    return@collect otherValues.send(it ?: NULL)
                }
            }

            var lastValue: Any? = null
            collect { value ->
                otherValues
                    .tryReceive()
                    .onSuccess { lastValue = it }

                emit(
                    transform(
                        value,
                        NULL.unbox(lastValue ?: return@collect)
                    ),
                )
            }
        }
    }
}

inline fun <A, B> Flow<A>.withLatestFrom(other: Flow<B>): Flow<Pair<A, B>> =
    withLatestFrom(other) { a, b -> a to b }
