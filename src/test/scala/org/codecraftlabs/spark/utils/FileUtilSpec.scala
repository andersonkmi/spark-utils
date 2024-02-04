package org.codecraftlabs.spark.utils

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.reflect.io.Path

class FileUtilSpec extends AnyFlatSpec with Matchers {
  "When directory option is empty" should "return empty list" in {
    val results = FileUtil.getListOfFiles(None, List(".scala"))
    results should not be None
    results shouldEqual List.empty
  }

  "When directory is ." should "return test case files" in {
    val currentDirectory = Path("src/test/scala/org/codecraftlabs/spark/utils").toAbsolute
    val results = FileUtil.getListOfFiles(Some(currentDirectory.toString()), List(".scala"))
    results should not be None
    results should not be List.empty
  }
}
