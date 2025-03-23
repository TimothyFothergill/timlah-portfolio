package com.timlah.models.components.button

import play.api.mvc.Call

case class Button(
    ariaLabel: String = "",
    additionalClasses: String = "",
    icon: Option[Icon] = None,
    id: String = "",
    jsScript: String = "",
    text: String = "",
    url: Either[String, Call] = Left(""),
)

object Button {
    implicit val defaultButton = Button()
}