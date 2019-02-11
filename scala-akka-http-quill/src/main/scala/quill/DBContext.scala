package quill

import io.getquill.{H2JdbcContext, SnakeCase}

class DBContext extends H2JdbcContext(SnakeCase, "db")
