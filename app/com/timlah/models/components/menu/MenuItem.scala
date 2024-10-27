package com.timlah.models.components.menu

case class MenuItem(
    additionalClasses: String = "",
    ariaLabel: Option[String] = None,
    text: String = "",
    icon: Option[MenuIcon] = None
)

object MenuItem {
    val default: MenuItem = MenuItem()
}
