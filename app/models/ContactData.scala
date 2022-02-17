package models

import play.api.data._
import play.api.data.Forms._

case class ContactData(
  name    : String        ,
  email   : Option[String],
  contents: String
) {
 override def equals(that: Any): Boolean = true
}

object ContactData {
  val contactForm: Form[ContactData] = Form(
   mapping(
    "name"      -> nonEmptyText           ,
    "email"     -> optional(email)        ,
    "contents"  -> nonEmptyText
   )(ContactData.apply)(ContactData.unapply))
}
