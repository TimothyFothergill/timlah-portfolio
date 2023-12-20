package com.timlah.connectors

import com.timlah.models.WalkAboutWithMe
import play.api.libs.json.Json
import requests.Response

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class WalkAboutWithMeConnector @Inject()(implicit val ec: ExecutionContext) {
  def getAllWalkAboutWithMeData: Future[Seq[WalkAboutWithMe]] = {
//    requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=45.395738&marker1long=141.702843&marker2lat=44.812031&marker2long=142.074650")
//    requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=45.395738&marker1long=141.702843&marker2lat=44.812031&marker2long=142.074650")
//    requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=44.812031&marker1long=142.074650&marker2lat=43.763066969072504&marker2long=142.35892064504907")
// end marker "3" 43.763066969072504, 142.35892064504907  https://www.youtube.com/watch?v=OteBEPtrfo4
// end marker "4" 43.331180951043265, 141.861271595367    https://www.youtube.com/watch?v=Jc0NTzrzD0U
// end marker "5" 43.0556336617387, 141.35340782203917    https://www.youtube.com/watch?v=PP3Izy5yX6s
// end marker "6" 42.45221975618502, 141.1802584555724    https://www.youtube.com/watch?v=ItrDsHDk9m8
// end marker "7" 42.51292878110876, 140.37600568798462   https://www.youtube.com/watch?v=_wGMQIpkCd4
// end marker "8" 41.773972222882, 140.72774963744067     https://www.youtube.com/watch?v=0Z-1J_wZRBQ
//  val r: Response = requests.get("http://localhost:8080/course?start_date=2022-10-16&end_date=2022-10-30&marker1lat=45.395738&marker1long=141.702843&marker2lat=44.812031&marker2long=142.074650&marker2lat=45.395738&marker2long=141.702843&marker2lat=43.763066969072504&marker2long=142.35892064504907&marker2lat=43.331180951043265&marker2long=141.861271595367&marker2lat=43.0556336617387&marker2long=141.35340782203917&marker2lat=42.45221975618502&marker2long=141.1802584555724&marker2lat=42.51292878110876&marker2long=140.37600568798462&marker2lat=41.773972222882&marker2long=140.72774963744067&videos=https://www.youtube.com/watch?v=pxXm_C8i1n0&videos=https://www.youtube.com/watch?v=cBA6Pdnnnhs&videos=https://www.youtube.com/watch?v=OteBEPtrfo4&videos=https://www.youtube.com/watch?v=Jc0NTzrzD0U&videos=https://www.youtube.com/watch?v=PP3Izy5yX6s&videos=https://www.youtube.com/watch?v=ItrDsHDk9m8&videos=https://www.youtube.com/watch?v=_wGMQIpkCd4&videos=https://www.youtube.com/watch?v=0Z-1J_wZRBQ")
    val r: Response = requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=43.0556336617387&marker1long=141.35340782203917&marker2lat=42.45221975618502&marker2long=141.1802584555724")
    Future(Json.parse(r.text()).as[Seq[WalkAboutWithMe]])
  }

  def scheduledJob: Unit = {
    requests.get("http://walk-about-with-me-api.herokuapp.com/?marker1lat=43.0556336617387&marker1long=141.35340782203917&marker2lat=42.45221975618502&marker2long=141.1802584555724")
  }
}
