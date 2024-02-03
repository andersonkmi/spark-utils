package org.codecraftlabs.spark.utils

import org.codecraftlabs.spark.utils.ArgsUtils.parseArgs
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ArgsUtilSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {

  "Null args" should "be not null" in {
    val result = parseArgs(null)
    result should not be null
  }

  "Null args" should "be empty" in {
    val result = parseArgs(null)
    result.size shouldEqual 0
  }

  "Empty args" should "be empty" in {
    val args = Array.empty[String]
    val result = parseArgs(args)
    result should not be null
    result.size shouldEqual 0
  }

  "Single args" should "return 1 empty arg" in {
    val args = Array("--test")
    an [InvalidArgsException] should be thrownBy parseArgs(args)
  }

  "Single arguments" should "return 1 valid arg" in {
    val args = Array("--test", "value")
    val result = parseArgs(args)
    result should not be null
    result.size shouldEqual 1
    result.head._1 shouldEqual "--test"
    result.head._2 shouldEqual "value"
  }

  "Double arguments" should "return 2 valid arguments" in {
    val args = Array("--test", "value", "--test2", "value2")
    val result = parseArgs(args)
    result should not be null
    result.size shouldEqual 2
    result.contains("--test") shouldEqual true
    result("--test") shouldEqual "value"
    result.contains("--test2") shouldEqual true
    result("--test2") shouldEqual "value2"
  }
}
