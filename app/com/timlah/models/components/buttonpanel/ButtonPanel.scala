package com.timlah.models.components.buttonpanel

import com.timlah.models.components.button.Button
import com.timlah.models.components.button.Icon.{PauseIcon,PlayIcon}
import com.timlah.models.components.button.ButtonIcon

case class ButtonPanel(
    ariaLabel: String = "",
    additionalClasses: String = "",
    buttons: Seq[Button] = Seq(),
    label: Option[String] = None
)

object ButtonPanel {
    val indexAnimationControls = ButtonPanel(
        ariaLabel = "background animation controls",
        additionalClasses = "",
        buttons = Seq(Button(
            ariaLabel = "",
            additionalClasses = "animation-control-button",
            icon = Some(PlayIcon),
            id = "play-button",
            jsScript = "playBackgroundAnimation()",
            text = ""
        ),
        Button(
            ariaLabel = "",
            additionalClasses = "animation-control-button",
            icon = Some(PauseIcon),
            id = "pause-button",
            jsScript = "pauseBackgroundAnimation()",
            text = ""
        )),
        label = Some("Background Controls")
    )
}