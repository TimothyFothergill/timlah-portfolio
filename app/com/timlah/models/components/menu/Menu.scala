package com.timlah.models.components.menu

case class Menu(
    ariaLabel: String = "",
    additionalClasses: String = "",
    menuItems: Seq[MenuItem] = Seq(),
    verticalLayout: Boolean = true
)

object Menu {
    val default: Menu = Menu()
}
