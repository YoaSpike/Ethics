name := """yoaspike_ethics"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtEbean)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.xerial" % "sqlite-jdbc" % "3.8.7",       // development database
  "org.mindrot" % "jbcrypt" % "0.3m",           // password hashing
  "com.adrianhurt" % "play-bootstrap3_2.11" % "0.4",    // bootstrap integration
  // pdf generation
  "com.google.code.maven-play-plugin.org.allcolor.yahp" % "yahp" % "1.3",
  "com.google.code.maven-play-plugin.org.allcolor.yahp" % "yahp-internal" % "1.3",
  "org.xhtmlrenderer" % "flying-saucer-pdf" % "9.0.7",
  "it.innove" % "play2-pdf" % "1.1.3",
  "com.typesafe.play" %% "play-mailer" % "2.4.0"
)
