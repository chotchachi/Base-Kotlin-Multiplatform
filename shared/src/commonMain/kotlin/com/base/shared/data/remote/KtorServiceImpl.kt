package com.base.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders

class KtorServiceImpl(
    private val httpClient: HttpClient
) : KtorService {
    override suspend fun getNumistaImageUrl(
        obverseImage: ByteArray
    ): String = httpClient.post(urlString = "https://en.numista.com/catalogue/upload_search_image.php?i=1") {
        headers {
            clear()
            append("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36 Edg/114.0.1823.55")
            append("upgrade-insecure-requests", "1")
            append("sec-fetch-user", "?1")
            append("sec-fetch-site", "same-origin")
            append("sec-fetch-mode", "navigate")
            append("sec-fetch-dest", "iframe")
            append("sec-ch-ua-platform", "\"Android\"")
            append("sec-ch-ua-mobile", "?1")
            append("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Microsoft Edge\";v=\"114\"")
            append("referer", "https://en.numista.com/catalogue/upload_search_image.php?i=1")
            append("origin", "https://en.numista.com")
            append("cookie", "tb=y; tc=y; tn=y; tp=y; tt=y; PHPSESSID=l9gnmpj5debqauuku4pj8522ol")
            append("cache-control", "max-age=0")
            append("accept-language", "vi,en-US;q=0.9,en;q=0.8")
            append("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
            append("authority", "en.numista.com")
        }
        setBody(
            MultiPartFormDataContent(
                formData {
                    append("upload", obverseImage, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpg")
                        append(HttpHeaders.ContentDisposition, "filename=\"file.jpg\"")
                    })
                }
            )
        )
    }.body()

    override suspend fun getNumistaDetectResult(
        firstImageId: String,
        secondImageId: String
    ): String = httpClient.get("https://en.numista.com/catalogue/index.php?r=&ct=coin&im1=$firstImageId&im2=$secondImageId") {
        headers {
            clear()
            append("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Mobile Safari/537.36 Edg/114.0.1823.55")
            append("upgrade-insecure-requests", "1")
            append("sec-fetch-user", "?1")
            append("sec-fetch-site", "same-origin")
            append("sec-fetch-mode", "navigate")
            append("sec-fetch-dest", "iframe")
            append("sec-ch-ua-platform", "\"Android\"")
            append("sec-ch-ua-mobile", "?1")
            append("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Microsoft Edge\";v=\"114\"")
            append("referer", "https://en.numista.com/catalogue/upload_search_image.php?i=1")
            append("origin", "https://en.numista.com")
            append("cookie", "tb=y; tc=y; tn=y; tp=y; tt=y; PHPSESSID=l9gnmpj5debqauuku4pj8522ol")
            append("cache-control", "max-age=0")
            append("accept-language", "vi,en-US;q=0.9,en;q=0.8")
            append("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
            append("authority", "en.numista.com")
        }
    }.body()
}