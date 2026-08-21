package com.timlah.models.components

case class ExternalImage(
    title: String = "",
    url: String = "",
    alt: Option[String] = None,
    linkContent: Option[String] = None,
    landscape: Boolean = true
)

object ExternalImage {
    def apply(appliedUrl: String): ExternalImage = ExternalImage(url = appliedUrl)
}
