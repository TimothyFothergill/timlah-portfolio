package models

import play.api.data._
import play.api.data.Forms._

case class ContactData(
  name    : String        ,
  email   : Option[String],
  subject : Option[String],
  enquiry : String        ,
  contents: String
) {
 override def equals(that: Any): Boolean = true
}

object ContactData {
  val contactForm: Form[ContactData] = Form(
   mapping(
    "name"      -> nonEmptyText    ,
    "email"     -> optional(email) ,
    "subject"   -> optional(text)  ,
    "enquiry"   -> text            ,
    "contents"  -> nonEmptyText
   )(ContactData.apply)(ContactData.unapply))
}
