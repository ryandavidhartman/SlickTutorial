import sbt._

object Dependencies {
  lazy val slick = "com.typesafe.slick" %% "slick" % "3.3.+"
  lazy val slf4j = "org.slf4j" % "slf4j-nop" % "1.7.26"
  lazy val hikariCP = "com.typesafe.slick" %% "slick-hikaricp" % "3.3.+"
  lazy val h2 = "com.h2database" % "h2" % "1.4.196"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
}
