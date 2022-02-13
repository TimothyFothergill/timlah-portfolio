package services

import play.api.libs.mailer._
import javax.inject.Inject

class EmailService @Inject() (mailerClient: MailerClient) {

  def sendEmail(name: String, address: String, content: String) = {
    val email = Email(
      subject   = "Enquiry from timlah.com",
      from      = f"$name <$address>",
      to        = Seq("me@timlah.com"),
      bodyText  = Some(f"$content"),
    )
    mailerClient.send(email)
  }
}
