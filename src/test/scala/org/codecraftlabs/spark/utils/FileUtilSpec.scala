package org.codecraftlabs.spark.utils

import org.codecraftlabs.spark.utils.FileUtil.getListOfFiles
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.reflect.io.Path

class FileUtilSpec extends AnyFlatSpec with Matchers {
  "When directory option is empty" should "return empty list" in {
    val results = getListOfFiles(None, List(".scala"))
    results should not be None
    results shouldEqual Set.empty
  }

  "When directory is ." should "return test case files" in {
    val currentDirectory = Path("src/test/scala/org/codecraftlabs/spark/utils").toAbsolute
    val results = getListOfFiles(Some(currentDirectory.toString()), List(".scala"))
    results should not be None
    results should not be Set.empty

    // Asserting the file names
    val expectedFileNames: Set[String] = Set(currentDirectory.toString() + "/ArgsUtilSpec.scala",
      currentDirectory.toString() + "/FileUtilSpec.scala")
    expectedFileNames.subsetOf(results) shouldEqual true
  }
}
