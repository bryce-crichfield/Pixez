ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "PixezTracker",
    idePackagePrefix := Some("org.bpc"),
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    // https://mvnrepository.com/artifact/com.miglayout/miglayout
    libraryDependencies += "com.miglayout" % "miglayout" % "3.7.4"

  )
