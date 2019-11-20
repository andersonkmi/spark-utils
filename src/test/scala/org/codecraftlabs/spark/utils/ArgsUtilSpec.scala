package org.codecraftlabs.spark.utils

import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers}

class ArgsUtilSpec extends FlatSpec with Matchers with BeforeAndAfterAll {

  "Empty args" should "return nothing" in {
    val result = ArgsUtils.parseArgs(null)
    result should not be null
  }
}
