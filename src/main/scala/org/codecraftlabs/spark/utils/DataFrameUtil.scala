package org.codecraftlabs.spark.utils

import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.spark.sql.functions.{asc, col}
import org.apache.spark.sql.{DataFrame, SaveMode}

object DataFrameUtil {
  @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)
  private val Header: String = "header"

  def saveDataFrameToCsv(df: DataFrame,
                          partitions: Int = 1,
                          destination: String,
                          saveMode: SaveMode = Overwrite,
                          header: Boolean = true) : Unit = {
    logger.debug(s"Saving data frame to csv with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    df.coalesce(partitions).write.mode(saveMode).option(Header, header).csv(destination)
  }

  def saveDataFrameToJson(df: DataFrame,
                           destination: String,
                           partitions: Int = 1,
                           saveMode: SaveMode = Overwrite,
                           header: Boolean = false): Unit = {
    logger.debug(s"Saving data frame to json with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    df.coalesce(partitions).write.mode(saveMode).json(destination)
  }

  def saveDataFrameToParquet(df: DataFrame,
                              destination: String,
                              partitions: Int = 1,
                              saveMode: SaveMode = Overwrite): Unit = {
    logger.debug(s"Saving data frame to parquet with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    df.coalesce(partitions).write.mode(saveMode).parquet(destination)
  }

  def extractDistinctValues(df: DataFrame,
                            columnName: String): DataFrame = {
    df.select(columnName).distinct.orderBy(asc(columnName))
  }

  def extractDistinctValues(df: DataFrame, columns: List[String]): DataFrame = {
    df.select(columns.map(col): _*).distinct()
  }
}
