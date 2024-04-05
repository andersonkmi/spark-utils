package org.codecraftlabs.spark.utils

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions.asc
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.codecraftlabs.spark.utils.DataFrameUtil.{extractDistinctValues, saveDataFrameToCsv, saveDataFrameToJson, saveDataFrameToParquet}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.reflect.io.Path

class DataFrameUtilSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  @transient var sparkSession: Option[SparkSession] = None
  private val CurrentDirectory = Path("src/test/scala/org/codecraftlabs/spark/utils").toAbsolute
  private val CsvFile = CurrentDirectory.toString + "/dataframe.csv"
  private val JsonFile = CurrentDirectory.toString + "/dataframe.json"
  private val ParquetFile = CurrentDirectory.toString + "/dataframe.parquet"

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
      Row("account002", "Bob", 2),
      Row("account003", "Bob", 2)
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
    saveDataFrameToCsv(df = dataFrame, destination = CsvFile)
  }

  "When saving the data frame as json" should "create a json file" in {
    val dataFrame = createDataFrame()
    saveDataFrameToJson(df = dataFrame, destination = JsonFile)
  }

  "When saving the data frame as parquet" should "create a parquet file" in {
    val dataFrame = createDataFrame()
    saveDataFrameToParquet(df = dataFrame, destination = ParquetFile)
  }

  "When extracting distinct values" should "return a new dataframe with unique values" in {
    val dataFrame = createDataFrame()
    val df = extractDistinctValues(dataFrame, "userName").orderBy(asc("userName"))
    df.count() shouldEqual 2
    val results = df.collect().map(_(0)).toList
    results.head shouldEqual "Bob"
    results(1) shouldEqual "Mike"
  }
}
