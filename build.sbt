val commonSettings = Seq(
  organization := "com.github.fomkin",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-Xfatal-warnings",
    "-language:postfixOps",
    "-language:implicitConversions"
  )
)

val `scala-reql-core` = crossProject.crossType(CrossType.Pure).
  settings(commonSettings:_*).
  settings(
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full),
    sourceGenerators in Compile <+= sourceManaged in Compile map ApiGenerator
  ).
  settings(libraryDependencies += "com.github.fomkin" %%% "pushka-json" % "0.3.0-SNAPSHOT")

lazy val `scala-reql-core-js` = `scala-reql-core`.js
lazy val `scala-reql-core-jvm` = `scala-reql-core`.jvm

lazy val `akka-reql` = project.
  dependsOn(`scala-reql-core-jvm`).
  settings(commonSettings:_*).
  settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % "2.3.7"
    )
  )
