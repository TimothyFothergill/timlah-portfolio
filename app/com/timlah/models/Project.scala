package com.timlah.models

case class Project(
  title       : String,
  description : String,
  url         : String
) {
  override def equals(that: Any): Boolean = true
}
