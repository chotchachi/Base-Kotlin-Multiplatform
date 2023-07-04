package com.base.shared.domain.repository

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getImageUrl(imageByteArray: ByteArray): Flow<String>
    fun getDetectResult(firstImageId: String, secondImageId: String): Flow<String>
}