package features

import akka.http.scaladsl.model.{ContentTypes, HttpEntity, StatusCodes}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import endpoint.UserEndpoint
import org.scalatest._
import quill.DBContext
import repositories.UserRepository

class UserFeature
  extends FeatureSpec
    with Matchers
    with ScalatestRouteTest {

  lazy val userEndpoint = new UserEndpoint(new UserRepository(new DBContext))

  lazy val routes = Route.seal(userEndpoint.routes)

  feature("User resource") {
    scenario("A client should be able to successfully create an user") {
      lazy val newUser =
        """
          |{
          |  "name": "Gabriel",
          |  "age": 26
          |}
        """.stripMargin

      Post("/users", HttpEntity(ContentTypes.`application/json`, newUser)) ~> routes ~> check {
        status shouldBe StatusCodes.Created
      }
    }

    scenario("A client should be able to successfully fetch an existing user") {
      lazy val newUser =
        """
          |{
          |  "name": "Gabriel",
          |  "age": 26
          |}
        """.stripMargin

      val Some(location) = Post("/users", HttpEntity(ContentTypes.`application/json`, newUser)) ~> routes ~> check {
        status shouldBe StatusCodes.Created

        header("Location").map(_.value())
      }

      Get(location) ~> routes ~> check {
        status shouldBe StatusCodes.OK
      }
    }
  }
}
