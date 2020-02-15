package org.codecraftlabs.spark.utils

object DataFormat extends Enumeration {
  type DataFormat = Value

  val CSV, JSON, PARQUET = Value
}
