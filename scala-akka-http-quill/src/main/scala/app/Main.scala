package app

import akka.http.scaladsl.server.{HttpApp, Route}
import endpoint.{HealthCheckEndpoint, UserEndpoint}
import quill.DBContext
import repositories.UserRepository

object Main extends HttpApp with App {
  lazy val userEndpoint = new UserEndpoint(new UserRepository(new DBContext))
  lazy val healthCheckEndpoint = new HealthCheckEndpoint

  override def routes: Route = userEndpoint.routes ~ healthCheckEndpoint.routes

  startServer("localhost", 8080)
}
