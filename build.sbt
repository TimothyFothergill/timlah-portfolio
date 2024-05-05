name := """timlah-portfolio"""
organization := "com.timlah"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.typesafe.play" %% "play-mailer" % "8.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "8.0.1"
libraryDependencies += "org.planet42" %% "laika-io" % "0.18.0"
libraryDependencies += "com.lihaoyi" %% "requests" % "0.6.9"
libraryDependencies += "commons-codec" % "commons-codec" % "1.15"

libraryDependencies ++= Seq(
  caffeine,
  "com.typesafe.play" %% "play-json-joda" % "2.7.4",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "org.postgresql" % "postgresql" % "42.2.12"
)
libraryDependencies ++=
  Seq(
    "com.andersen-gott" %% "scravatar" % "1.0.4"
  )
val AkkaVersion = "2.6.8"
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.timlah.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.timlah.binders._"
