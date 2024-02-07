package org.codecraftlabs.spark.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.reflect.io.Path

class DataFrameUtilSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  @transient var sparkSession: Option[SparkSession] = None
  private val CurrentDirectory = Path("src/test/scala/org/codecraftlabs/spark/utils").toAbsolute
  private val CsvFile = CurrentDirectory.toString + "/dataframe.csv"

  override def beforeAll(): Unit = {
    val sparkConfig = new SparkConf()
    sparkConfig.set("spark.broadcast.compress", "false")
    sparkConfig.set("spark.shuffle.compress", "false")
    sparkConfig.set("spark.shuffle.spill.compress", "false")
    sparkConfig.set("spark.master", "local")
    sparkSession = Some(SparkSession.builder().config(sparkConfig).getOrCreate())
  }

  private def createDataFrame(): DataFrame = {
    val sampleData = Seq(
    Row("account001", "Mike", 1),
    Row("account002", "Bob", 2)
    )
    val schema = schemaDefinition()
    sparkSession.get.createDataFrame(sparkSession.get.sparkContext.parallelize(sampleData), schema)
  }

  private def schemaDefinition(): StructType = {
    val accountName         = StructField("accountName", StringType, nullable = false)
    val userName            = StructField("userName", StringType, nullable = false)
    val userId              = StructField("userId", IntegerType, nullable = false)

    val fields = List(accountName, userName, userId)
    StructType(fields)
  }

  "When saving the data frame as csv" should "create a CSV file" in {
    val dataFrame = createDataFrame()
    DataFrameUtil.saveDataFrameToCsv(df = dataFrame, destination = CsvFile)
  }
}
