organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.2.5" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val `hello-lagom` = (project in file("."))
  .aggregate(`hello-lagom-api`, `hello-lagom-impl`, `hello-lagom-stream-api`, `hello-lagom-stream-impl`,
  `helloworld-lagom-consumer-api`, `hello-lagom-consumer-impl`, `welcome-api`, `welcome-impl`)

lazy val `hello-lagom-api` = (project in file("hello-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-impl` = (project in file("hello-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`hello-lagom-api`)

lazy val `hello-lagom-stream-api` = (project in file("hello-lagom-stream-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `hello-lagom-stream-impl` = (project in file("hello-lagom-stream-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`hello-lagom-stream-api`, `hello-lagom-api`)

lazy val `helloworld-lagom-consumer-api` = (project in file("helloworld-lagom-consumer-api"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )

lazy val `hello-lagom-consumer-impl` = (project in file("hello-lagom-consumer-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      lagomScaladslKafkaBroker,
      lagomScaladslKafkaClient,
      lagomScaladslPersistenceCassandra,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`helloworld-lagom-consumer-api`, `hello-lagom-api`)

lazy val `welcome-api` = (project in file("welcome-api"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )

lazy val `welcome-impl` = (project in file("welcome-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslTestKit,
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      macwire,
      scalaTest
    )
  )
  .dependsOn(`welcome-api`)

//lagomKafkaEnabled in ThisBuild := false
//lagomKafkaAddress in ThisBuild := "localhost:9092"
