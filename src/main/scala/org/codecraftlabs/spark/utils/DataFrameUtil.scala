package org.codecraftlabs.spark.utils

import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SaveMode}

object DataFrameUtil {
  private val Header: String = "header"

  def saveDataFrameToCsv(df: DataFrame,
                          partitions: Int = 1,
                          destination: String,
                          saveMode: SaveMode = Overwrite,
                          header: Boolean = true) : Unit = {
    df.coalesce(partitions).write.mode(saveMode).option(Header, header).csv(destination)
  }

  def saveDataFrameToJson(df: DataFrame,
                           destination: String,
                           partitions: Int = 1,
                           saveMode: SaveMode = Overwrite): Unit = {
    df.coalesce(partitions).write.mode(saveMode).json(destination)
  }

  def saveDataFrameToParquet(df: DataFrame,
                              destination: String,
                              partitions: Int = 1,
                              saveMode: SaveMode = Overwrite): Unit = {
    df.coalesce(partitions).write.mode(saveMode).parquet(destination)
  }

  def extractDistinctValues(df: DataFrame,
                            columnName: String): DataFrame = {
    df.select(columnName).distinct()
  }

  def extractDistinctValues(df: DataFrame, columns: List[String]): DataFrame = {
    df.select(columns.map(col): _*).distinct()
  }
}
