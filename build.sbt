organization := "org.codecraftlabs.spark"

name := "spark-utils"

val appVersion = "1.3.0"

val appName = "spark-utils"

version := appVersion

scalaVersion := "2.13.10"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.5.0",
  "org.apache.spark" %% "spark-sql" % "3.5.0",
  "org.scalatest" %% "scalatest" % "3.2.17" % "test"
)

//Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.ScalaLibrary
//Test / classLoaderLayeringStrategy := ClassLoaderLayeringStrategy.Flat
//fork in Test := true
//javaOptions ++= Seq("-Xms512M", "-Xmx4096M", "-XX:+CMSClassUnloadingEnabled")