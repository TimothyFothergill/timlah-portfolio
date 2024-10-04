package com.timlah.models.components.header

case class Header(
    text: String = "",
    additionalClasses: String = "",
    headerLevel: Option[Int] = None,
    href: Option[String] = None,
    anchor: Boolean = false
)

object Header {
    implicit val defaultHeader = Header()
}
