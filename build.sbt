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
libraryDependencies ++= Seq(
  "org.reactivemongo" %% "play2-reactivemongo" % "0.20.13-play28",
  "org.reactivemongo" %% "reactivemongo-play-json-compat" % "1.0.1-play28",
  "org.reactivemongo" %% "reactivemongo-bson-compat" % "0.20.13",
  "com.typesafe.play" %% "play-json-joda" % "2.7.4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.timlah.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.timlah.binders._"
