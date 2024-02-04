package org.codecraftlabs.spark.utils

import org.apache.log4j.Logger
import org.apache.spark.sql.SaveMode.Overwrite
import org.apache.spark.sql.{Dataset, SaveMode}

object DataSetUtil {
  @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)
  private val Header: String = "header"

  def saveDataSetToCsv[T](ds: Dataset[T],
                           partitions: Int = 1,
                           destination: String,
                           saveMode: SaveMode = Overwrite,
                           header: Boolean = true) : Unit = {
    logger.debug(s"Saving data set to csv with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).option(Header, header).csv(destination)
  }

  def saveDataSetToJson[T](ds: Dataset[T],
                            destination: String,
                            partitions: Int = 1,
                            saveMode: SaveMode = Overwrite,
                            header: Boolean = false): Unit = {
    logger.debug(s"Saving data set to json with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).json(destination)
  }

  def saveDataSetToParquet[T](ds: Dataset[T],
                               destination: String,
                               partitions: Int = 1,
                               saveMode: SaveMode = Overwrite): Unit = {
    logger.debug(s"Saving data set to parquet with $partitions partitions, mode '${saveMode.name()}', destination '$destination'")
    ds.coalesce(partitions).write.mode(saveMode).parquet(destination)
  }

}
