package com.timlah.models.admin

import com.timlah.models.Author
import akka.http.scaladsl.model.DateTime
import java.util.Date

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class NewBlogPostForm(
  title     : String          ,
  slug      : String          ,
  content   : String          ,
  date      : DateTime
)

object NewBlogPostForm {

  val dateTimeMapping: Mapping[DateTime] = date("yyyy-MM-dd").transform(
    date => DateTime(date.getTime / 1000),
    akkaDateTime => new Date(akkaDateTime.clicks * 1000)
  )

  val newBlogPostForm: Form[NewBlogPostForm] = Form(
    mapping(
        "title"   -> nonEmptyText,
        "slug"    -> nonEmptyText,
        "content" -> nonEmptyText,
        "date"    -> dateTimeMapping,
    )(NewBlogPostForm.apply)(NewBlogPostForm.unapply))
}
