package com.timlah.models

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class ContactData(
  name    : String        ,
  email   : String        ,
  subject : Option[String],
  enquiry : String        ,
  contents: String
)

object ContactData {
  val contactForm: Form[ContactData] = Form(
   mapping(
    "name"      -> nonEmptyText    ,
    "email"     -> email           ,
    "subject"   -> optional(text)  ,
    "enquiry"   -> text            ,
    "contents"  -> nonEmptyText
   )(ContactData.apply)(ContactData.unapply))
}

sealed trait EnquiryType {
 def asString: String
}
object EnquiryType {
 case object BusinessEnquiry extends EnquiryType { override val asString = "Business Enquiry" }
 case object Feedback        extends EnquiryType { override val asString = "Feedback"         }
 case object Other           extends EnquiryType { override val asString = "Other"            }

 val values: List[EnquiryType] = List(BusinessEnquiry, Feedback, Other)
 def parse(s: String): Option[EnquiryType] = values.find(_.asString == s)

 implicit def queryStringBindable(implicit strBinder: QueryStringBindable[String]): QueryStringBindable[EnquiryType] =
  new QueryStringBindable[EnquiryType] {
   override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, EnquiryType]] =
    params.get(key).flatMap(_.headOption.map(EnquiryType.parse)).map {
     case None       => Left(s"Unsupported enquiry type")
     case Some(enq)  => Right(enq)
    }

   override def unbind(key: String, value: EnquiryType): String =
    strBinder.unbind(key, value.asString)
  }
}