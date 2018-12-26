package org.codecraftlabs.spark.utils

import org.apache.spark.sql.DataFrame

object DataUtils {
  def saveDataSetToCsv(ds: DataFrame, partitions: Int = 1, destination: String, saveMode: String = "overwrite", header: Boolean = true) : Unit = {
    ds.coalesce(partitions).write.mode(saveMode).option("header", header).csv(destination)
  }
}
