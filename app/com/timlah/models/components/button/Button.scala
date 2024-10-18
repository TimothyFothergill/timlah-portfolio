package com.timlah.models.components.button

case class Button(
    ariaLabel: String = "",
    additionalClasses: String = "",
    icon: Option[ButtonIcon] = None,
    text: String = "",
    url: Option[String] = Some("#")
)

object Button {
    implicit val defaultButton = Button()
}