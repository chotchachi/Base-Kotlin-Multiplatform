package com.base.shared.domain.repository

import com.base.shared.AppCoroutineDispatchers
import com.base.shared.data.local.RealmPersistence
import com.base.shared.data.remote.KtorService
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlHandler
import com.mohamedrejeb.ksoup.html.parser.KsoupHtmlParser
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json

class RepositoryImpl(
    private val ktorService: KtorService,
    private val realmPersistence: RealmPersistence,
    private val dispatchers: AppCoroutineDispatchers,
    private val json: Json
) : Repository {
    override fun getImageUrl(imageByteArray: ByteArray): Flow<String> = flow {
        emit(ktorService.getNumistaImageUrl(imageByteArray))
    }.map {
        // Use a regular expression to extract the image URL from the HTML
        val regex = """<img src="([^"]+)" class="image_filter" alt="">""".toRegex()
        val matchResult = regex.find(it)
        val imageUrl = matchResult?.groupValues?.get(1) ?: throw NullPointerException()
        return@map imageUrl.substring(imageUrl.lastIndexOf('/') + 1)
    }.flowOn(dispatchers.io)

    override fun getDetectResult(firstImageId: String, secondImageId: String): Flow<String> = flow {
        emit(ktorService.getNumistaDetectResult(firstImageId, secondImageId))
    }.onEach { htmlString ->
        parseResultHtml(htmlString).forEach {
            Napier.d { it.toString() }
        }
    }.flowOn(dispatchers.io)

    private fun parseResultHtml(htmlString: String): List<CoinParse> {
        val listCoin = mutableListOf<CoinParse>()
        var tempCoin: CoinParse? = null
        var tempTitle = ""
//        var tempDescription: String? = null
        var waitingPhotoAvers = false
        var waitingPhotoRevers = false
        var waitingDescriptionPrice = false
//        var waitingDescriptionUrl = false
        var waitingDescriptionTitle = false
//        var waitingDescriptionInfo = false

        val handler = KsoupHtmlHandler
            .Builder()
            .onOpenTag { name, attributes, _ ->
                Napier.d { "Open tag: $name - attributes: $attributes" }
                if (attributes["class"].equals("resultat_recherche")) {
                    tempCoin = CoinParse()
                    return@onOpenTag
                }
                if (attributes["class"].equals("photo_avers") || waitingPhotoAvers) {
                    waitingPhotoAvers = true
                    if (name == "img") {
                        tempCoin?.firstImage = attributes["src"] ?: ""
                        waitingPhotoAvers = false
                    }
                    return@onOpenTag
                }
                if (attributes["class"].equals("photo_revers") || waitingPhotoRevers) {
                    waitingPhotoRevers = true
                    if (name == "img") {
                        tempCoin?.secondImage = attributes["src"] ?: ""
                        waitingPhotoRevers = false
                    }
                    return@onOpenTag
                }
                if (attributes["class"].equals("description_piece") || waitingDescriptionPrice) {
                    waitingDescriptionPrice = true
                    if (name == "a" && tempCoin?.url == null) {
                        tempCoin?.url = attributes["href"] ?: ""
                    }
                    if (name == "br") {
                        waitingDescriptionTitle = true
                    }
                    if (name == "em") {
                        waitingDescriptionTitle = false
                        tempCoin?.title = tempTitle
                        tempTitle = ""
                        if (tempCoin != null) {
                            listCoin.add(tempCoin!!)
                            tempCoin = null
                        }
                    }
                    return@onOpenTag
                }
            }
            .onText {
                Napier.d { ">>> $it" }
                if (waitingDescriptionTitle) {
                    tempTitle += it.trimIndent()
                }
            }
            .build()

        val ksoupHtmlParser = KsoupHtmlParser(
            handler = handler,
        )

        ksoupHtmlParser.write(htmlString)

        ksoupHtmlParser.end()

        return listCoin
    }
}

class CoinParse {
    var firstImage: String = ""
    var secondImage: String = ""
    var title: String = ""
    var description: String = ""
    var url: String = ""

    override fun toString(): String {
        return "$firstImage - $secondImage - $title - $url \n"
    }
}