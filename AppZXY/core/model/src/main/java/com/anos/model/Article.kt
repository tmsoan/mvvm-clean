package com.anos.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@Root(name = "item", strict = false)
data class Article(
    /**
     * @param title the title to set
     */
    @field:Element(name = "title")
    var title: String? = null,

    /**
     * @param link the link to set
     */
    @field:Element(name = "link")
    var link: String? = null,

    /**
     * @param description the description to set
     */
    @field:Element(name = "description")
    var description: String? = null,

    /**
     * @param pubDate the pubDate to set
     */
    @field:Element(name = "pubDate")
    var pubDate: String? = null,

    /**
     * @param enclosure the enclosure to set
     */
    @field:Element(name = "enclosure", required = false)
    var enclosure: Enclosure? = null
) {
    fun formatPubDate(): String {
        return runCatching {
            val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm", Locale.ENGLISH)
            val parsedDate: Date = inputFormat.parse(pubDate)
            outputFormat.format(parsedDate)
        }.getOrElse { pubDate.orEmpty() }
    }
}

@Root(name = "enclosure", strict = false)
data class Enclosure(
    @field:Attribute(name = "url", required = true)
    var url: String = "",

    @field:Attribute(name = "length", required = false)
    var length: Long? = null,

    @field:Attribute(name = "type", required = false)
    var type: String? = null
)
