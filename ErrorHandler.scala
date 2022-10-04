import javax.inject.Singleton
import play.api.http.HttpErrorHandler
import play.api.http.Status._
import play.api.mvc.Results._
import play.api.mvc._
import scala.concurrent._

@Singleton
class ErrorHandler extends HttpErrorHandler {
  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    Future.successful(
      NotFound(com.timlah.views.html.notfound())
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    Future.successful(
      InternalServerError(com.timlah.views.html.internalservererror())
    )
  }
}