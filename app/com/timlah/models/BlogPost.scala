package com.timlah.models

import akka.http.scaladsl.model.DateTime
import play.api.libs.json._

case class BlogPost(
  id        : Int             ,
  author    : Author          ,
  coauthor  : Option[Author]  ,
  title     : String          ,
  slug      : String          ,
  content   : String          ,
  date      : DateTime
)

case class StoredBlogPost(
  id        : Int             ,
  author    : Author          ,
  coauthor  : Option[Author]  ,
  title     : String          ,
  slug      : String          ,
  content   : String          ,
  date      : String
)
