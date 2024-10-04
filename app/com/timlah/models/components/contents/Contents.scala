package com.timlah.models.components.contents

case class Contents(
    additionalClasses: String = "",
    contentsItems: Seq[String] = Seq(),
    text: String = "Contents"
)

object Contents {
    val default: Contents = Contents()
}
