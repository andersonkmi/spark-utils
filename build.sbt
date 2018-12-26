import Dependencies._

organization := "org.codecraftlabs.spark"

name := "spark-utils"

val appVersion = "1.0.0.0"

val appName = "spark-utils"

version := appVersion

scalaVersion := "2.11.8"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.0",
  "org.apache.spark" %% "spark-sql" % "2.4.0",
  "org.json4s" %% "json4s-jackson" % "3.6.2",
  "com.databricks" % "spark-csv_2.11" % "1.5.0",
  scalaTest % Test
)
