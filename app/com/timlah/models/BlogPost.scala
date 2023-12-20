package com.timlah.models

import akka.http.scaladsl.model.DateTime

case class BlogPost(
  id        : Int             ,
  author    : Author          ,
  coauthor  : Option[Author]  ,
  title     : String          ,
  slug      : String          ,
  content   : String          ,
  date      : DateTime
)