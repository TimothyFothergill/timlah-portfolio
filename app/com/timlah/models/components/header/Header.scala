package com.timlah.models.components.header

case class Header(
    text: String = "",
    additionalClasses: String = "",
    headerLevel: Option[Int] = None
)

object Header {
    implicit val defaultHeader = Header()
}
