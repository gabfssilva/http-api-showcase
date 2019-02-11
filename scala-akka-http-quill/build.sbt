name := "scala-akka-http-quill"

version := "0.1"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

val akkaHttp = "10.1.7"
val akka = "2.5.19"
val macwire = "2.3.0"
val circe = "0.11.1"
val quill = "3.0.1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttp,
  "com.typesafe.akka" %% "akka-stream" % akka,
  "com.typesafe.akka" %% "akka-slf4j" % akka,

  "com.h2database" % "h2" % "1.4.197",
  "io.getquill" %% "quill-jdbc" % quill,

  "de.heikoseeberger" %% "akka-http-circe" % "1.24.3",

  "com.beachape" %% "enumeratum" % "1.5.12",
  "com.beachape" %% "enumeratum-circe" % "1.5.12",

  "ch.qos.logback"  %  "logback-classic"   % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",

  "io.circe" %% "circe-core" % circe,
  "io.circe" %% "circe-generic" % circe,
  "io.circe" %% "circe-generic-extras" % circe,
  "io.circe" %% "circe-parser" % circe,

  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttp % "test",
  "com.typesafe.akka" %% "akka-testkit" % akka % "test",
  "com.typesafe.akka" %% "akka-stream-testkit" % akka % "test"
)
