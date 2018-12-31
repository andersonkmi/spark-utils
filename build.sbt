import Dependencies._

organization := "org.codecraftlabs.spark"

name := "spark-utils"

val appVersion = "1.0.2"

val appName = "spark-utils"

version := appVersion

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.401",
  "com.databricks" %% "spark-csv" % "1.5.0",
  scalaTest % Test
)
