package org.codecraftlabs.spark.utils

object DataFormat extends Enumeration {
  type DataFormat = Value

  val CSV: Value = Value("csv")
  val JSON: Value  = Value("json")
  val PARQUET: Value = Value("parquet")
}
