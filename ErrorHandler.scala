import javax.inject.Singleton
import play.api.http.HttpErrorHandler
import play.api.http.Status._
import play.api.mvc.Results._
import play.api.mvc._
import scala.concurrent._

import javax.inject._
import com.timlah.models.netbitpet.{NetBitPet,Food}
import com.timlah.services.netbitpet._

@Singleton
class ErrorHandler @Inject()(
  netbitpetPlayService          : NetBitPetPlayService
) extends HttpErrorHandler {
  override def onClientError(request: RequestHeader, statusCode: Int, message: String): Future[Result] = {
    val meh = netbitpetPlayService.newNBPCatto()
    Future.successful(
      NotFound(com.timlah.views.html.notfound())
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable): Future[Result] = {
    val meh = netbitpetPlayService.newNBPCatto()
    Future.successful(
      InternalServerError(com.timlah.views.html.internalservererror())
    )
  }
}