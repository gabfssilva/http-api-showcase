package repositories

import java.util.concurrent.Executors

import domain.{User, UserSchema}
import quill.DBContext

import scala.concurrent.{ExecutionContext, Future}

class UserRepository(val ctx: DBContext) extends UserSchema {
  import ctx._

  implicit lazy val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(10))

  def insert(user: User): Future[User] = Future {
    val createdUserId: Long = run {
      quote {
        users.insert(lift(user)).returning(_.id)
      }
    }

    user.copy(id = createdUserId)
  }

  def fetchById(id: Long): Future[Option[User]] = Future {
    val result: List[User] = run {
      quote {
        users.filter(user => user.id == lift(id))
      }
    }

    result.headOption
  }
}
