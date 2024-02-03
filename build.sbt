organization := "org.codecraftlabs.spark"

name := "spark-utils"

val appVersion = "1.2.9"

val appName = "spark-utils"

version := appVersion

scalaVersion := "2.13.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.3",
  "org.apache.spark" %% "spark-sql" % "3.3.2",
  "org.scalatest" %% "scalatest" % "3.2.17" % "test"
)
