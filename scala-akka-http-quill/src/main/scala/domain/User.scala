package domain

import quill.DBContext

case class User(id: Long = -1,
                name: String,
                age: Int)

trait UserSchema {
  val ctx: DBContext

  import ctx._

  val users = quote {
    querySchema[User]("user_table",
      _.id -> "idt_user",
      _.name -> "desc_name",
      _.age -> "num_age"
    )
  }
}