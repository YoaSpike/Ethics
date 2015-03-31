name := """yoaspike_ethics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "org.xerial" % "sqlite-jdbc" % "3.8.7",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "com.adrianhurt" % "play-bootstrap3_2.11" % "0.4"
)
