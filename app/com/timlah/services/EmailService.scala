package com.timlah.services

import play.api.libs.mailer._
import javax.inject.Inject

class EmailService @Inject() (mailerClient: MailerClient) {

  def sendEmail(subject: String, name: String, address: String, enquiry: String, content: String, remoteAddress: String): String = {
    val email = Email(
      subject   = subject,
      from      = f"$name <$address>",
      to        = Seq("timlah@timlah.com"),
      bodyText  = Some(f"Reason for enquiry: $enquiry \n\n $content \n\n email sender address: $address \n\n remoteAddress: $remoteAddress"),
    )
    mailerClient.send(email)
  }
}
