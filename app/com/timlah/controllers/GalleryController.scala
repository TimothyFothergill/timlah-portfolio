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

  val baseUrl = "https://i.ibb.co/"

  def kitacon2024() = Action { implicit request: Request[AnyContent] =>
    Ok(
      com.timlah.views.html.galleries.kitacon2024(
        Seq(
          ExternalImage(
            "Kane Cosplay by Lewis (metalguy666)",
            s"${baseUrl}kB0cz7j/Kitacon-2024-3.jpg"),
          ExternalImage(
            "Friday night party courtesy of DJ LastKnight",
            s"${baseUrl}nCPWW4Y/Kitacon-2024-26.jpg",
            linkContent = Some("https://www.twitch.tv/Geekclubnights")
          ),
          ExternalImage(
            "Bray Wyatt Cosplay by me",
            s"${baseUrl}P9g1m9Z/Kitacon-2024-21.jpg",
            alt = Some("Working lantern and all!"),
            landscape = false
          ),
          // testing
          ExternalImage(s"${baseUrl}cDCqc8Z/Kitacon-2024-24.jpg"),
          // testing
          ExternalImage(
            "Jessica Fletcher cosplay by LaterLevels",
            s"${baseUrl}SB5vtCH/Kitacon-2024-32.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            "Cosplay Masquerade entries",
            s"${baseUrl}bsf7K6z/Kitacon-2024-34.jpg"),
          ExternalImage(s"${baseUrl}H7KHffN/Kitacon-2024-35.jpg"),
          ExternalImage(s"${baseUrl}8KG0Z2g/Kitacon-2024-36.jpg"),
          ExternalImage(s"${baseUrl}qYbstC4/Kitacon-2024-37.jpg"),
          ExternalImage(s"${baseUrl}stpNt3v/Kitacon-2024-38.jpg"),
          ExternalImage(s"${baseUrl}V2wV6FD/Kitacon-2024-39.jpg"),
          ExternalImage(s"${baseUrl}z482DnQ/Kitacon-2024-40.jpg"),
          ExternalImage(s"${baseUrl}FVX9jyM/Kitacon-2024-41.jpg"),
          ExternalImage(s"${baseUrl}RyLcgb9/Kitacon-2024-42.jpg"),
          ExternalImage(s"${baseUrl}XXNfNX5/Kitacon-2024-43.jpg"),
          ExternalImage(s"${baseUrl}PNDZ4DQ/Kitacon-2024-44.jpg"),
          ExternalImage(s"${baseUrl}TBZzX5k/Kitacon-2024-45.jpg"),
          ExternalImage(s"${baseUrl}C6pZ2f0/Kitacon-2024-46.jpg"),
          ExternalImage(s"${baseUrl}9bkTtYW/Kitacon-2024-47.jpg"),
          ExternalImage(s"${baseUrl}y8PgtPp/Kitacon-2024-48.jpg"),
          ExternalImage(s"${baseUrl}RBj3L9W/Kitacon-2024-49.jpg"),
          ExternalImage(s"${baseUrl}D1cbk9Z/Kitacon-2024-50.jpg"),
          ExternalImage(s"${baseUrl}ZSzxqh9/Kitacon-2024-51.jpg"),
          ExternalImage(s"${baseUrl}QY0NTky/Kitacon-2024-52.jpg"),
          ExternalImage(s"${baseUrl}fqmFNBq/Kitacon-2024-53.jpg"),
          ExternalImage(s"${baseUrl}Q8xdg9x/Kitacon-2024-54.jpg"),
          ExternalImage(s"${baseUrl}bNhZ34F/Kitacon-2024-55.jpg"),
          ExternalImage(s"${baseUrl}g6tR3y7/Kitacon-2024-56.jpg"),
          ExternalImage(s"${baseUrl}DDzxpzL/Kitacon-2024-57.jpg"),
          ExternalImage(s"${baseUrl}28m9Fb7/Kitacon-2024-58.jpg"),
          ExternalImage(s"${baseUrl}QkLRKK6/Kitacon-2024-59.jpg"),
          ExternalImage(s"${baseUrl}k5wk3gp/Kitacon-2024-60.jpg"),
          ExternalImage(s"${baseUrl}ZTzfNKM/Kitacon-2024-61.jpg"),
          ExternalImage(s"${baseUrl}Krj3Sfq/Kitacon-2024-62.jpg"),
          ExternalImage(s"${baseUrl}nbjVNqC/Kitacon-2024-63.jpg"),
          ExternalImage(s"${baseUrl}zST2NTM/Kitacon-2024-64.jpg"),
          ExternalImage(s"${baseUrl}K9WQq9B/Kitacon-2024-65.jpg"),
          ExternalImage(s"${baseUrl}gMctM7J/Kitacon-2024-66.jpg"),
          ExternalImage(s"${baseUrl}Sf6rzW0/Kitacon-2024-67.jpg"),
          ExternalImage(
            "Kitacon Taskmaster",
            s"${baseUrl}b3ZWRJV/Kitacon-2024-68.jpg"),
          ExternalImage(s"${baseUrl}Fw9bbWm/Kitacon-2024-69.jpg"),
          ExternalImage(s"${baseUrl}mNcjvcs/Kitacon-2024-70.jpg"),
          ExternalImage(
            "Me as Detective Gumshoe from Phoenix Wright",
            s"${baseUrl}jgmXLsH/Kitacon-2024-71.jpg",
            landscape = false
          ),
          ExternalImage(
            "DJ LastKnight vs DJ Shenny Rock Party",
            s"${baseUrl}T4Bj0nX/Kitacon-2024-phone-1.jpg",
            linkContent = Some("https://www.twitch.tv/Geekclubnights")
          ),
          ExternalImage(
            "LaterLevels as Molly and Sam from Ghost",
            s"${baseUrl}kMWHsN6/Kitacon-2024-76.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            "Setup for Kitas Got Talent",
            s"${baseUrl}mvNb62S/Kitacon-2024-77.jpg"),
          ExternalImage(
            "Kitas Got Talent entries",
            s"${baseUrl}JkW8ByW/Kitacon-2024-78.jpg"),
          ExternalImage(s"${baseUrl}D9XHjQx/Kitacon-2024-79.jpg"),
          ExternalImage(s"${baseUrl}jrYryhv/Kitacon-2024-80.jpg"),
          ExternalImage(s"${baseUrl}gV15SKH/Kitacon-2024-81.jpg"),
          ExternalImage(s"${baseUrl}4WHd37t/Kitacon-2024-82.jpg"),
          ExternalImage(s"${baseUrl}FXtHn7k/Kitacon-2024-83.jpg"),
          ExternalImage(
            "Closing Ceremony Awards",
            s"${baseUrl}cNQNz54/Kitacon-2024-85.jpg"),
          ExternalImage(
            "LaterLevels as Mrs Doyle from Father Ted",
            s"${baseUrl}3pbm1xG/kitacon-2024-021.jpg",
            linkContent = Some("https://laterlevels.com/")
          ),
          ExternalImage(
            "Awesome Pokémon Gyarados prop",
            s"${baseUrl}yB3K0hn/kitacon-2024-022.jpg",
            alt = Some("Gyarados prop that shoots water"),
          ),
          ExternalImage(
            "Obligatory game of Magic: the Gathering",
            s"${baseUrl}ZYhPmCF/kitacon-2024-023.jpg"),
          ExternalImage(
            "Phil with a lantern",
            s"${baseUrl}D8ns1tR/kitacon-2024-024.jpg"),
          ExternalImage(
            "My fiancé, Jake, with glowstick ears",
            s"${baseUrl}PxKNGzQ/kitacon-2024-025.jpg")
        )
      )
    )
  }

  def kitacon2026() = Action { implicit request: Request[AnyContent] =>
    Ok(
      com.timlah.views.html.galleries.kitacon2026(
        Seq(
            ExternalImage(
                "Radcliffe is a lovely venue on campus at Warwick University",
                s"${baseUrl}PzgSTZqF/kitacon26-3.jpg"),
            ExternalImage(
                "Wonderful tote bags with every ticket",
                s"${baseUrl}yn9qrMZL/kitacon26-4.jpg"),
            ExternalImage(
                "Paid for extra merch, extra money goes to charity",
                s"${baseUrl}Qv7dDq4d/kitacon26-5.jpg"),
            ExternalImage(
                "A drink on the terrace bar on a hot day",
                s"${baseUrl}wZPhDf4R/kitacon26-6.jpg"),
            ExternalImage(
                "The Cabaret show this year was really good fun",
                s"${baseUrl}RkHKc7v0/kitacon26-8.jpg"),
            ExternalImage(s"${baseUrl}6JnfJqyq/kitacon26-9.jpg"),
            ExternalImage(s"${baseUrl}HLb291TJ/kitacon26-12.jpg"),
            ExternalImage(s"${baseUrl}ZpSsZsHx/kitacon26-13.jpg"),
            ExternalImage(s"${baseUrl}6cw55z4t/kitacon26-14.jpg"),
            ExternalImage(s"${baseUrl}tpbHYhrT/kitacon26-15.jpg"),
            ExternalImage(s"${baseUrl}hFTLQ5Cv/kitacon26-16.jpg"),
            ExternalImage(
                "First night's party",
                s"${baseUrl}bgYCpzr2/kitacon26-19.jpg"),
            ExternalImage(s"${baseUrl}fGr7gBmc/kitacon26-20.jpg"),
            ExternalImage(
                "Me as Taro Sakamoto",
                s"${baseUrl}vCdMGyQR/kitacon26-21.jpg"),
            ExternalImage(
                "Dealer's hall had lots of great merch",
                s"${baseUrl}d8cq0xq/kitacon26-22.jpg"),
            ExternalImage(s"${baseUrl}MyLvQLtN/kitacon26-23.jpg"),
            ExternalImage(
                "Look at all the Gundam kits!",
                s"${baseUrl}3YBxLb48/kitacon26-24.jpg"),
            ExternalImage(s"${baseUrl}G49vprNP/kitacon26-25.jpg"),
            ExternalImage(s"${baseUrl}N6JqMbLM/kitacon26-26.jpg"),
            ExternalImage(
                "Playing some Bongos in the Video Games room",
                s"${baseUrl}ymKPCBR2/kitacon26-27.jpg"),
            ExternalImage(
                "There was a great showing for an Idol festival this year",
                s"${baseUrl}JWCQ2JNf/kitacon26-31.jpg"),
            ExternalImage(
                "Idol fans with their light sticks",
                s"${baseUrl}jZ665QJL/kitacon26-33.jpg"),
            ExternalImage(s"${baseUrl}1YjtYSZP/kitacon26-34.jpg"),
            ExternalImage(
                s"${baseUrl}wh8CPN5W/kitacon26-35.jpg"),
            ExternalImage(
                "An amazing Zidane and Steiner from FF9",
                s"${baseUrl}qMXnrXPS/kitacon26-36.jpg"),
            ExternalImage(
                "Videos on the Kitacon YouTube channel",
                s"${baseUrl}0yW759jv/kitacon26-37.jpg",
                linkContent = Some("https://www.youtube.com/@KitaconUK")
            ),
            ExternalImage(s"${baseUrl}20qs34hK/kitacon26-39.jpg"),
            ExternalImage(s"${baseUrl}tM4zX1F8/kitacon26-40.jpg"),
            ExternalImage(s"${baseUrl}WWVQz1Mc/kitacon26-42.jpg"),
            ExternalImage(s"${baseUrl}WWrtC07x/kitacon26-44.jpg"),
            ExternalImage(s"${baseUrl}hJWmRFdM/kitacon26-45.jpg"),
            ExternalImage(s"${baseUrl}j98tXJ8X/kitacon26-46.jpg"),
            ExternalImage(s"${baseUrl}Dh0PXdP/kitacon26-47.jpg"),
            ExternalImage(s"${baseUrl}7xsJnWfv/kitacon26-48.jpg"),
            ExternalImage(s"${baseUrl}XZLXqYcx/kitacon26-49.jpg"),
            ExternalImage(s"${baseUrl}R4Nnpzfc/kitacon26-50.jpg"),
            ExternalImage(
                "The winning entrant to the Cosplay Masquerade, as Master Roshi",
                s"${baseUrl}nqzt7LmF/kitacon26-51.jpg"),
            ExternalImage(s"${baseUrl}7JL1z8ty/kitacon26-52.jpg"),
            ExternalImage(s"${baseUrl}PZ8PqYb2/kitacon26-53.jpg"),
            ExternalImage(s"${baseUrl}HDtSVNb6/kitacon26-54.jpg"),
            ExternalImage(s"${baseUrl}bRyqSNdN/kitacon26-55.jpg"),
            ExternalImage(s"${baseUrl}DfKSVwZy/kitacon26-56.jpg"),
            ExternalImage(s"${baseUrl}LhtPLtyB/kitacon26-57.jpg"),
            ExternalImage(s"${baseUrl}211ZzXjY/kitacon26-58.jpg"),
            ExternalImage(
                "Night 2's party - Rock and metal night!",
                s"${baseUrl}mgc95Fc/kitacon26-60.jpg"),
            ExternalImage(s"${baseUrl}d4sJNcpC/kitacon26-61.jpg"),
            ExternalImage(s"${baseUrl}t5vkd17/kitacon26-62.jpg"),
            ExternalImage(
                "Dealer's hall on Sunday",
                s"${baseUrl}nqq4nrnJ/kitacon26-63.jpg"),
            ExternalImage(s"${baseUrl}KjZXgR3k/kitacon26-64.jpg"),
            ExternalImage(s"${baseUrl}HDfFLJ8g/kitacon26-65.jpg"),
            ExternalImage(
                "A series of amazing performances in the talent show",
                s"${baseUrl}bt9whJ8/kitacon26-67.jpg"),
            ExternalImage(s"${baseUrl}8Lgv96jp/kitacon26-68.jpg"),
            ExternalImage(s"${baseUrl}JWTf3tbg/kitacon26-69.jpg"),
            ExternalImage(s"${baseUrl}PvH4Czhz/kitacon26-70.jpg"),
            ExternalImage(s"${baseUrl}Q79r4Cdz/kitacon26-71.jpg"),
            ExternalImage(s"${baseUrl}fdqLt9Z8/kitacon26-72.jpg"),
            ExternalImage(s"${baseUrl}NdpLms6H/kitacon26-73.jpg"),
            ExternalImage(s"${baseUrl}jPYgkpXc/kitacon26-74.jpg"),
            ExternalImage(s"${baseUrl}NnYGrwDC/kitacon26-75.jpg"),
            ExternalImage(s"${baseUrl}5h5vdqQ3/kitacon26-76.jpg"),
            ExternalImage(s"${baseUrl}7JVrCGCd/kitacon26-77.jpg"),
            ExternalImage(s"${baseUrl}mC95Ys8N/kitacon26-78.jpg"),
            ExternalImage(s"${baseUrl}QFfRY2GD/kitacon26-79.jpg"),
            ExternalImage(s"${baseUrl}fVnsR7yP/kitacon26-80.jpg"),
            ExternalImage(s"${baseUrl}4RFdrhqW/kitacon26-81.jpg"),
            ExternalImage(s"${baseUrl}s9fVGWQX/kitacon26-82.jpg"),
            ExternalImage(
                "The closing ceremony",
                s"${baseUrl}7d5NT90y/kitacon26-83.jpg"),
            ExternalImage(
                "Here began the charity auction, raising more money for Cancer Research UK",
                s"${baseUrl}nMWxYy64/kitacon26-84.jpg"),
            ExternalImage(s"${baseUrl}fVwLddKx/kitacon26-85.jpg"),
            ExternalImage(s"${baseUrl}kgBhVhHN/kitacon26-86.jpg"),
            ExternalImage(s"${baseUrl}C3wM1G6V/kitacon26-88.jpg"),
            ExternalImage(s"${baseUrl}b5KMrL2n/kitacon26-89.jpg"),
            ExternalImage(s"${baseUrl}4w2vgzkQ/kitacon26-90.jpg"),
            ExternalImage(s"${baseUrl}TJzhZD6/kitacon26-91.jpg"),
            ExternalImage(
                "Outside the final night's party, so many costumes still on display!",
                s"${baseUrl}ZrmZz1S/kitacon26-92.jpg"),
            ExternalImage(
                "The final countdown to the end of Kitacon 2026 - See you next time!",
                s"${baseUrl}W4Z2MN46/kitacon26-93.jpg")
        )
      )
    )
  }

    def severnbridge2026() = Action { implicit request: Request[AnyContent] =>
      Ok(
        com.timlah.views.html.galleries.severnbridge2026(
          Seq(
            ExternalImage(
              "Underpass to bridge artwork",
              s"${baseUrl}9kGcJM3h/001-underpass-1.jpg"),
            ExternalImage(s"${baseUrl}pjtFJQPT/002-underpass-2.jpg"),
            ExternalImage(s"${baseUrl}kgQRSgVg/003-underpass-3.jpg"),
            ExternalImage(s"${baseUrl}wFCjpDGS/004-underpass-4.jpg"),
            ExternalImage(s"${baseUrl}Ng4KmbRV/005-underpass-5.jpg"),
            ExternalImage(
              "Path from underpass to the bridge",
              s"${baseUrl}ZqHvH3C/006-path-to-bridge-1.jpg"),
            ExternalImage(
              "Start of Wye Bridge",
              s"${baseUrl}zWTWzZft/007-start-of-bridge-7.jpg"),
            ExternalImage(s"${baseUrl}pvSt74Mz/008-start-of-bridge-8.jpg"),
            ExternalImage(
              "Overlooking from the Welsh side of the bridge",
              s"${baseUrl}KpjQJ4q1/009-overlooking-welsh-side-of-bridge-9.jpg"),
            ExternalImage(
              "Time for a lot of steps!",
              s"${baseUrl}YBFKgVrX/010-on-bridge-10.jpg"),
            ExternalImage(s"${baseUrl}bgDJrCT7/011-on-bridge-11.jpg"),
            ExternalImage(
              "Overlooking the bridge at England",
              s"${baseUrl}ZR1zL9Jk/012-bridge-england-side-12.jpg"),
            ExternalImage(
              "Plaques for those who worked on the bridge",
              s"${baseUrl}v4m6R42w/013-bridge-plaque-13.jpg"),
            ExternalImage(s"${baseUrl}YFRCPj2P/014-bridge-plaque-2-14.jpg"),
            ExternalImage(
              "A footpath to footbridge over the M48",
              s"${baseUrl}ZzVdCXx1/015-footpath-to-services-15.jpg"),
            ExternalImage(
              "Footbridge over the M48",
              s"${baseUrl}W4Jp415p/016-footbridge-to-services-16.jpg"),
            ExternalImage(
              "Severn View Moto, free to park for 2 hours",
              s"${baseUrl}twctdjk5/017-severn-view-moto-services-17.jpg",
              linkContent = Some("https://moto-way.com/services/severn-view/")
            ),
            ExternalImage(
              "Cheeky Costa",
              s"${baseUrl}5gKzgCxd/018-costa-in-services-18.jpg"),
            ExternalImage(
              "Going home to Wales",
              s"${baseUrl}BV56L43w/019-england-to-wales-bridge-side-19.jpg"),
            ExternalImage(
              "Near the military base on Wales side",
              s"${baseUrl}nG8XTfM/020-england-to-wales-bridge-side-passing-military-base-20.jpg"),
            ExternalImage(
              "Path down to the underpass",
              s"${baseUrl}1YT5ZJJF/021-path-from-bridge-to-underpass-21.jpg"),
            ExternalImage(
              "Catbus in the underpass!",
              s"${baseUrl}QvnwVhj8/022-underpass-entrance-from-bridge-22.jpg"),
            ExternalImage(
              "More incredible underpass art",
              s"${baseUrl}W428t7Jz/023-underpass-23.jpg"),
          )
        )
      )
    }
  }
