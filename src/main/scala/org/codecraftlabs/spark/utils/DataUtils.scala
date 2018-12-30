package org.codecraftlabs.spark.utils

import org.apache.spark.sql.DataFrame

object DataUtils {
  def saveDataSetToCsv(ds: DataFrame, partitions: Int = 1, destination: String, saveMode: String = "overwrite", header: Boolean = true) : Unit = {
    ds.coalesce(partitions).write.mode(saveMode).option("header", header).csv(destination)
  }

  def saveDataFrameToJson(df: DataFrame, destination: String, partitions: Int = 1, saveMode: String = "overwrite", header: Boolean = false): Unit = {
    df.coalesce(partitions).write.mode(saveMode).json(destination)
  }
}
