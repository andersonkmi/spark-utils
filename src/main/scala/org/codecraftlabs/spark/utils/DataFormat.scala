package org.codecraftlabs.spark.utils

object DataFormat extends Enumeration {
  type DataFormat = String

  val CSV = "csv"
  val JSON  = "json"
  val PARQUET = "parquet"
}
