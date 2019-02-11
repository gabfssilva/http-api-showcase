package endpoint

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.server.Route
import domain.User
import repositories.UserRepository

import scala.util.{Failure, Success}

class UserEndpoint(repository: UserRepository) extends JsonEndpoint {
  def insertRoute: Route = post {
    entity(as[User]) { user =>
      onComplete(repository.insert(user)) {
        case Failure(e) =>
          logger.error(e.getMessage, e)
          complete(StatusCodes.InternalServerError)
        case Success(createdUser) =>
          respondWithHeader(Location(s"/users/${createdUser.id}")) {
            complete(StatusCodes.Created)
          }
      }
    }
  }

  def fetchRoute: Route = get {
    path(LongNumber) { id =>
      onComplete(repository.fetchById(id)) {
        case Failure(e) =>
          logger.error(e.getMessage, e)
          complete(StatusCodes.InternalServerError)
        case Success(Some(createdUser)) =>
          complete(StatusCodes.OK -> createdUser)
        case Success(None) =>
          complete(StatusCodes.NotFound)
      }
    }
  }

  override def routes: Route = pathPrefix("users") {
    fetchRoute ~ insertRoute
  }
}
