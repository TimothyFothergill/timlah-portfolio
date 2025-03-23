package com.timlah.models.admin

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formatter
import play.api.mvc.QueryStringBindable

case class AdminLoginDetails(
  username  : String,
  password  : String
)

object AdminLoginDetails {
  val adminLoginForm: Form[AdminLoginDetails] = Form(
   mapping(
    "username"  -> nonEmptyText    ,
    "password"  -> nonEmptyText
   )(AdminLoginDetails.apply)(AdminLoginDetails.unapply))
}