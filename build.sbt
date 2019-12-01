import Dependencies._

organization := "org.codecraftlabs.spark"

name := "spark-utils"

val appVersion = "1.1.5"

val appName = "spark-utils"

version := appVersion

scalaVersion := "2.12.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.4",
  "org.apache.spark" %% "spark-sql" % "2.4.4",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.678",
  scalaTest % Test
)
