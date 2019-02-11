package endpoint

import akka.http.scaladsl.server.{Directives, Route}
import com.typesafe.scalalogging.Logger
import de.heikoseeberger.akkahttpcirce.ErrorAccumulatingCirceSupport
import io.circe.generic.extras.AutoDerivation
import io.circe.generic.extras.Configuration

trait Endpoint extends Directives {
  lazy val logger = Logger(this.getClass.getSimpleName)

  def routes: Route
}

trait JsonEndpoint
  extends Endpoint
    with AutoDerivation
    with ErrorAccumulatingCirceSupport {

  implicit val config: Configuration = Configuration.default.withDefaults
}
