package com.anos.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
data class Article(
    /**
     * @param title the title to set
     */
    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    /**
     * @param link the link to set
     */
    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    /**
     * @param description the description to set
     */
    @field:Element(name = "description")
    @param:Element(name = "description")
    var description: String? = null
)
