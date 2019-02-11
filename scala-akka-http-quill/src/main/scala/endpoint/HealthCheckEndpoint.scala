package endpoint

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route

class HealthCheckEndpoint extends Endpoint {
  override def routes: Route = path("health-check")(get(complete(StatusCodes.OK)))
}
