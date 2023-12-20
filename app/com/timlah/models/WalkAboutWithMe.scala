package com.timlah.models


import play.api.libs.json._
import play.api.libs.functional.syntax._

case class WalkAboutWithMe(
  id              : Int,
  date            : String,
  markers         : Markers,
  progressImageURL: String,
  videoURL        : String
)

object WalkAboutWithMe {
  implicit val walkAboutWithMeFormat: OFormat[WalkAboutWithMe] = (
    (__ \ "id"                ).format[Int]
    ~ (__ \ "date"            ).format[String]
    ~ (__ \ "markers"         ).format[Markers]
    ~ (__ \ "videoURL"        ).format[String]
    ~ (__ \ "progressImageURL").format[String]
    )(WalkAboutWithMe.apply _, unlift(WalkAboutWithMe.unapply))
}

case class Marker(
  lat  : Float,
  long : Float,
)

object Marker {
  implicit val markerFormat: OFormat[Marker] =
      ((__ \ "lat" ).format[Float]
      ~(__ \ "long").format[Float]
  )(Marker.apply _, unlift(Marker.unapply))
}

case class Markers(
  startMarker     : Marker,
  endMarker       : Marker,
  progressMarker  : Marker
)

object Markers {
  implicit val markersFormat: OFormat[Markers] =
    ((__ \ "startMarker"      ).format[Marker]
      ~ (__ \ "endMarker"     ).format[Marker]
      ~ (__ \ "progressMarker").format[Marker]
    )(Markers.apply _, unlift(Markers.unapply))
}
