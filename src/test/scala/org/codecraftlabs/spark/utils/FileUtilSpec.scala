package org.codecraftlabs.spark.utils

import org.codecraftlabs.spark.utils.FileUtil.getListOfFiles
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.io.File
import scala.reflect.io.Path

class FileUtilSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  private val CurrentDirectory = Path("src/test/scala/org/codecraftlabs/spark/utils").toAbsolute
  private val EmptyFile1 = CurrentDirectory.toString + "/empty1.txt"
  private val EmptyFile2 = CurrentDirectory.toString + "/empty2.txt"
  private val ExpectedFiles = Set(CurrentDirectory.toString() + "/ArgsUtilSpec.scala", CurrentDirectory.toString() + "/FileUtilSpec.scala")

  override def beforeAll(): Unit = {
    createEmptyFile(EmptyFile1)
    createEmptyFile(EmptyFile2)
  }

  override def afterAll(): Unit = {
    removeFile(EmptyFile1)
    removeFile(EmptyFile2)
  }

  "When passing a directory name and a file name" should "return a full path name" in {
    val resultingName = FileUtil.buildFilePath("/home/test/folder", "test.txt")
    resultingName shouldEqual "/home/test/folder/test.txt"
  }

  "When directory option is empty" should "return empty list" in {
    val results = getListOfFiles(None, List(".scala"))
    results should not be None
    results shouldEqual Set.empty
  }

  "When directory is ." should "return test case files" in {
    val results = getListOfFiles(Some(CurrentDirectory.toString()), List(".scala"))
    results should not be None
    results should not be Set.empty

    // Asserting the file names
    ExpectedFiles.subsetOf(results) shouldEqual true
  }

  private def createEmptyFile(filePath: String): Unit = {
    val file = new File(filePath)
    if (file.createNewFile()) {
      println(s"File created: ${file.getName}")
    } else {
      println("File already exists.")
    }
  }

  private def removeFile(filePath: String): Unit = {
    val file = new File(filePath)
    file.delete();
  }
}
