package org.codecraftlabs.spark.utils

import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, Dataset}
import org.apache.spark.sql.functions.asc

object DataUtils {
  @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)

  def saveDataFrameToCsv(df: DataFrame, partitions: Int = 1, destination: String, saveMode: String = "overwrite", header: Boolean = true) : Unit = {
    logger.debug(s"Saving data frame to csv with ${partitions} partitions, mode '${saveMode}', destination '${destination}'")
    df.coalesce(partitions).write.mode(saveMode).option("header", header).csv(destination)
  }

  def saveDataSetToCsv[T](ds: Dataset[T], partitions: Int = 1, destination: String, saveMode: String = "overwrite", header: Boolean = true) : Unit = {
    logger.debug(s"Saving data set to csv with $partitions partitions, mode '$saveMode', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).option("header", header).csv(destination)
  }

  def saveDataFrameToJson(df: DataFrame, destination: String, partitions: Int = 1, saveMode: String = "overwrite", header: Boolean = false): Unit = {
    logger.debug(s"Saving data frame to json with $partitions partitions, mode '$saveMode', destination '$destination'")
    df.coalesce(partitions).write.mode(saveMode).json(destination)
  }

  def saveDataSetToJson[T](ds: Dataset[T], destination: String, partitions: Int = 1, saveMode: String = "overwrite", header: Boolean = false): Unit = {
    logger.debug(s"Saving data set to json with $partitions partitions, mode '$saveMode', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).json(destination)
  }

  def saveDataFrameToParquet(df: DataFrame, destination: String, partitions: Int = 1, saveMode: String = "overwrite"): Unit = {
    logger.debug(s"Saving data frame to parquet with $partitions partitions, mode '$saveMode', destination '$destination'")
    df.coalesce(partitions).write.mode(saveMode).parquet(destination)
  }

  def saveDataSetToParquet[T](ds: Dataset[T], destination: String, partitions: Int = 1, saveMode: String = "overwrite"): Unit = {
    logger.debug(s"Saving data frame to parquet with $partitions partitions, mode '$saveMode', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).parquet(destination)
  }

  def extractDistinctValues(contents: DataFrame, columnName: String): DataFrame = {
    contents.select(contents(columnName)).distinct.orderBy(asc(columnName))
  }
}
