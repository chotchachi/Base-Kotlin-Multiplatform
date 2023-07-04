package com.base.shared.data.remote

interface KtorService {
    suspend fun getNumistaImageUrl(obverseImage: ByteArray): String
    suspend fun getNumistaDetectResult(firstImageId: String, secondImageId: String): String
}