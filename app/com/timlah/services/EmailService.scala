package com.timlah.services

import play.api.libs.mailer._
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import play.api.Logging

class EmailService @Inject() (mailerClient: MailerClient)(implicit ec: ExecutionContext)
    extends Logging {

  def sendEmail(
      subject: String,
      name: String,
      address: String,
      enquiry: String,
      content: String
  ): Unit = {
    val email = Email(
      subject = subject,
      from = f"$name <$address>",
      to = Seq("timlah@timlah.com"),
      bodyText = Some(
        f"Reason for enquiry: $enquiry \n\n $content \n\n email sender address: $address \n\n"
      )
    )

    Future {
      try {
        val msgId = mailerClient.send(email)
        logger.info(s"Email sent (msgId=$msgId) subject=$subject")
      } catch {
        case err: Throwable =>
          logger.error("Failed to send contact email", err)
      }
    }
  }
}
