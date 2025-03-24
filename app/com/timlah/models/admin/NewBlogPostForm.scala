package com.timlah.models.admin

import com.timlah.models.{Author, BlogPost}
import java.util.Date

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class NewBlogPostForm(
  title     : String          ,
  slug      : String          ,
  content   : String          ,
  date      : String
)

object NewBlogPostForm {

  val newBlogPostForm: Form[NewBlogPostForm] = Form(
    mapping(
        "title"   -> nonEmptyText,
        "slug"    -> nonEmptyText,
        "content" -> nonEmptyText,
        "date"    -> nonEmptyText,
    )(NewBlogPostForm.apply)(NewBlogPostForm.unapply))
}
