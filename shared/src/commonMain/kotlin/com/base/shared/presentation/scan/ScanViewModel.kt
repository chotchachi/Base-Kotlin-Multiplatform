package com.base.shared.presentation.scan

import com.base.shared.domain.repository.Repository
import com.base.shared.extensions.withLatestFrom
import com.base.shared.utils.Result
import com.base.shared.utils.asResult
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn

@ExperimentalCoroutinesApi
class ScanViewModel(
    repository: Repository
) : ViewModel() {
    private val intentMutableFlow =
        MutableSharedFlow<ScanIntent>(extraBufferCapacity = Int.MAX_VALUE)
    private val intentSharedFlow: SharedFlow<ScanIntent> get() = intentMutableFlow

    @NativeCoroutinesState
    val state: StateFlow<Result<String>>

    init {
        val getImageIdTrigger = intentSharedFlow
            .filterIsInstance<ScanIntent.GetImageID>()

        val retryTrigger = intentSharedFlow
            .filterIsInstance<ScanIntent.Retry>()
            .withLatestFrom(getImageIdTrigger) { _, getImageId -> getImageId }

        state = merge(getImageIdTrigger, retryTrigger)
            .flatMapLatest {
                repository.getImageUrl(it.image)
                    .asResult()
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                Result.Idle
            )

        intentSharedFlow
            .filterIsInstance<ScanIntent.GetDetectResult>()
            .flatMapLatest {
                repository.getDetectResult(it.firstImageId, it.secondImageId)
                    .asResult()
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                Result.Idle
            )
    }

    fun processIntent(intent: ScanIntent) =
        check(intentMutableFlow.tryEmit(intent)) { "Failed to emit intent: $intent" }
}