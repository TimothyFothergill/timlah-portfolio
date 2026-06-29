package com.timlah.controllers

import com.timlah.models.components.ExternalImage
import play.api.mvc._
import play.twirl.api.Html

import javax.inject._
import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.language.postfixOps
import java.lang.ProcessBuilder.Redirect

import play.api.mvc.{Cookie, Request, Result}
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Singleton
class GalleryController @Inject() (
    cc: MessagesControllerComponents
)(implicit executionContext: ExecutionContext)
    extends MessagesAbstractController(cc) {
  def kitacon2024() = Action { implicit request: Request[AnyContent] =>
    Ok(
      com.timlah.views.html.galleries.kitacon2024(
        Seq(
          ExternalImage(
            None,
            "Kane Cosplay by Lewis (metalguy666)",
            "https://i.ibb.co/kB0cz7j/Kitacon-2024-3.jpg"
          ),
          ExternalImage(
            None,
            "Friday night party courtesy of DJ LastKnight",
            "https://i.ibb.co/nCPWW4Y/Kitacon-2024-26.jpg",
            linkContent = Some("https://www.twitch.tv/Geekclubnights")
          ),
          ExternalImage(
            Some("Working lantern and all!"),
            "Bray Wyatt Cosplay by me",
            "https://i.ibb.co/P9g1m9Z/Kitacon-2024-21.jpg",
            landscape = false
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/cDCqc8Z/Kitacon-2024-24.jpg"
          ),
          ExternalImage(
            None,
            "Jessica Fletcher cosplay by LaterLevels",
            "https://i.ibb.co/SB5vtCH/Kitacon-2024-32.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            None,
            "Cosplay Masquerade entries",
            "https://i.ibb.co/bsf7K6z/Kitacon-2024-34.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/H7KHffN/Kitacon-2024-35.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/8KG0Z2g/Kitacon-2024-36.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/qYbstC4/Kitacon-2024-37.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/stpNt3v/Kitacon-2024-38.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/V2wV6FD/Kitacon-2024-39.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/z482DnQ/Kitacon-2024-40.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/FVX9jyM/Kitacon-2024-41.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/RyLcgb9/Kitacon-2024-42.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/XXNfNX5/Kitacon-2024-43.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/PNDZ4DQ/Kitacon-2024-44.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/TBZzX5k/Kitacon-2024-45.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/C6pZ2f0/Kitacon-2024-46.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/9bkTtYW/Kitacon-2024-47.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/y8PgtPp/Kitacon-2024-48.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/RBj3L9W/Kitacon-2024-49.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/D1cbk9Z/Kitacon-2024-50.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/ZSzxqh9/Kitacon-2024-51.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/QY0NTky/Kitacon-2024-52.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/fqmFNBq/Kitacon-2024-53.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/Q8xdg9x/Kitacon-2024-54.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/bNhZ34F/Kitacon-2024-55.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/g6tR3y7/Kitacon-2024-56.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/DDzxpzL/Kitacon-2024-57.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/28m9Fb7/Kitacon-2024-58.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/QkLRKK6/Kitacon-2024-59.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/k5wk3gp/Kitacon-2024-60.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/ZTzfNKM/Kitacon-2024-61.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/Krj3Sfq/Kitacon-2024-62.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/nbjVNqC/Kitacon-2024-63.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/zST2NTM/Kitacon-2024-64.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/K9WQq9B/Kitacon-2024-65.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/gMctM7J/Kitacon-2024-66.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/Sf6rzW0/Kitacon-2024-67.jpg"
          ),
          ExternalImage(
            None,
            "Kitacon Taskmaster",
            "https://i.ibb.co/b3ZWRJV/Kitacon-2024-68.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/Fw9bbWm/Kitacon-2024-69.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/mNcjvcs/Kitacon-2024-70.jpg"
          ),
          ExternalImage(
            None,
            "Me as Detective Gumshoe from Phoenix Wright",
            "https://i.ibb.co/jgmXLsH/Kitacon-2024-71.jpg",
            landscape = false
          ),
          ExternalImage(
            None,
            "DJ LastKnight vs DJ Shenny Rock Party",
            "https://i.ibb.co/T4Bj0nX/Kitacon-2024-phone-1.jpg",
            linkContent = Some("https://www.twitch.tv/Geekclubnights")
          ),
          ExternalImage(
            None,
            "LaterLevels as Molly and Sam from Ghost",
            "https://i.ibb.co/kMWHsN6/Kitacon-2024-76.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            None,
            "Setup for Kitas Got Talent",
            "https://i.ibb.co/mvNb62S/Kitacon-2024-77.jpg"
          ),
          ExternalImage(
            None,
            "Kitas Got Talent entries",
            "https://i.ibb.co/JkW8ByW/Kitacon-2024-78.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/D9XHjQx/Kitacon-2024-79.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/jrYryhv/Kitacon-2024-80.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/gV15SKH/Kitacon-2024-81.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/4WHd37t/Kitacon-2024-82.jpg"
          ),
          ExternalImage(
            None,
            "",
            "https://i.ibb.co/FXtHn7k/Kitacon-2024-83.jpg"
          ),
          ExternalImage(
            None,
            "Closing Ceremony Awards",
            "https://i.ibb.co/cNQNz54/Kitacon-2024-85.jpg"
          ),
          ExternalImage(
            None,
            "LaterLevels as Mrs Doyle from Father Ted",
            "https://i.ibb.co/3pbm1xG/kitacon-2024-021.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            Some("Gyarados prop that shoots water"),
            "Awesome Pokémon Gyarados prop",
            "https://i.ibb.co/yB3K0hn/kitacon-2024-022.jpg"
          ),
          ExternalImage(
            None,
            "Obligatory game of Magic: the Gathering",
            "https://i.ibb.co/ZYhPmCF/kitacon-2024-023.jpg"
          ),
          ExternalImage(
            None,
            "Phil with a lantern",
            "https://i.ibb.co/D8ns1tR/kitacon-2024-024.jpg"
          ),
          ExternalImage(
            None,
            "My fiancé, Jake, with glowstick ears",
            "https://i.ibb.co/PxKNGzQ/kitacon-2024-025.jpg"
          )
        )
      )
    )
  }

    def severnbridge2026() = Action { implicit request: Request[AnyContent] =>
      Ok(
        com.timlah.views.html.galleries.severnbridge2026(
          Seq(
            ExternalImage(
              None,
              "Underpass to bridge artwork",
              "https://i.ibb.co/9kGcJM3h/001-underpass-1.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/pjtFJQPT/002-underpass-2.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/kgQRSgVg/003-underpass-3.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/wFCjpDGS/004-underpass-4.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/Ng4KmbRV/005-underpass-5.jpg"
            ),
            ExternalImage(
              None,
              "Path from underpass to the bridge",
              "https://i.ibb.co/ZqHvH3C/006-path-to-bridge-1.jpg"
            ),
            ExternalImage(
              None,
              "Start of Wye Bridge",
              "https://i.ibb.co/zWTWzZft/007-start-of-bridge-7.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/pvSt74Mz/008-start-of-bridge-8.jpg"
            ),
            ExternalImage(
              None,
              "Overlooking from the Welsh side of the bridge",
              "https://i.ibb.co/KpjQJ4q1/009-overlooking-welsh-side-of-bridge-9.jpg"
            ),
            ExternalImage(
              None,
              "Time for a lot of steps!",
              "https://i.ibb.co/YBFKgVrX/010-on-bridge-10.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/bgDJrCT7/011-on-bridge-11.jpg"
            ),
            ExternalImage(
              None,
              "Overlooking the bridge at England",
              "https://i.ibb.co/ZR1zL9Jk/012-bridge-england-side-12.jpg"
            ),
            ExternalImage(
              None,
              "Plaques for those who worked on the bridge",
              "https://i.ibb.co/v4m6R42w/013-bridge-plaque-13.jpg"
            ),
            ExternalImage(
              None,
              "",
              "https://i.ibb.co/YFRCPj2P/014-bridge-plaque-2-14.jpg"
            ),
            ExternalImage(
              None,
              "A footpath to footbridge over the M48",
              "https://i.ibb.co/ZzVdCXx1/015-footpath-to-services-15.jpg"
            ),
            ExternalImage(
              None,
              "Footbridge over the M48",
              "https://i.ibb.co/W4Jp415p/016-footbridge-to-services-16.jpg"
            ),
            ExternalImage(
              None,
              "Severn View Moto, free to park for 2 hours",
              "https://i.ibb.co/twctdjk5/017-severn-view-moto-services-17.jpg",
              linkContent = Some("https://moto-way.com/services/severn-view/")
            ),
            ExternalImage(
              None,
              "Cheeky Costa",
              "https://i.ibb.co/5gKzgCxd/018-costa-in-services-18.jpg"
            ),
            ExternalImage(
              None,
              "Going home to Wales",
              "https://i.ibb.co/BV56L43w/019-england-to-wales-bridge-side-19.jpg"
            ),
            ExternalImage(
              None,
              "Near the military base on Wales side",
              "https://i.ibb.co/nG8XTfM/020-england-to-wales-bridge-side-passing-military-base-20.jpg"
            ),
            ExternalImage(
              None,
              "Path down to the underpass",
              "https://i.ibb.co/1YT5ZJJF/021-path-from-bridge-to-underpass-21.jpg"
            ),
            ExternalImage(
              None,
              "Catbus in the underpass!",
              "https://i.ibb.co/QvnwVhj8/022-underpass-entrance-from-bridge-22.jpg"
            ),
            ExternalImage(
              None,
              "More incredible underpass art",
              "https://i.ibb.co/W428t7Jz/023-underpass-23.jpg"
            ),
          )
        )
      )
    }
  }
