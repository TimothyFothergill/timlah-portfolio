package com.timlah.models.components

case class ExternalImage(
    alt: Option[String],
    title: String,
    url: String,
    linkContent: Option[String] = None,
    landscape: Boolean = true
)
