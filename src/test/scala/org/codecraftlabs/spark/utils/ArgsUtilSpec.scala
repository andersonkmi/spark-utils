package org.codecraftlabs.spark.utils

import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class ArgsUtilSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  "Null args" should "be not null" in {
    val result = ArgsUtils.parseArgs(null)
    result should not be null
  }

  "Null args" should "be empty" in {
    val result = ArgsUtils.parseArgs(null)
    result.size shouldEqual 0
  }

  "Empty args" should "be empty" in {
    val args = Array.empty[String]
    val result = ArgsUtils.parseArgs(args)
    result should not be null
    result.size shouldEqual 0
  }

  "Single args" should "return 1 empty arg" in {
    val args = Array("--test")
    an [InvalidArgsException] should be thrownBy ArgsUtils.parseArgs(args)
  }

  "Single arguments" should "return 1 valid arg" in {
    val args = Array("--test", "value")
    val result = ArgsUtils.parseArgs(args)
    result should not be null
    result.size shouldEqual 1
    result.head._1 shouldEqual "--test"
    result.head._2 shouldEqual "value"
  }

  "Double arguments" should "return 2 valid arguments" in {
    val args = Array("--test", "value", "--test2", "value2")
    val result = ArgsUtils.parseArgs(args)
    result should not be null
    result.size shouldEqual 2
    result.head._1 shouldEqual "--test"
    result.head._2 shouldEqual "value"
    result.last._1 shouldEqual "--test2"
    result.last._2 shouldEqual "value2"
  }
}
