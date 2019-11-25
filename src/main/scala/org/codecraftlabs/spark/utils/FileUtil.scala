package org.codecraftlabs.spark.utils

import java.io.File
import java.nio.file.FileSystems.getDefault

object FileUtil {
  def getListOfFiles(dir: String): List[String] = {
    val root = new File(dir)

    if (!(root.exists && root.isDirectory)) List.empty[String] else getRecursiveListOfFiles(root).map(item => item.getAbsolutePath).filter(item => item.endsWith(".csv") || item.endsWith(".parquet")).toList
  }

  def getListOfFiles(dir: String, extensions: List[String]): List[String] = {
    val root = new File(dir)

    if (!(root.exists && root.isDirectory)) List[String]() else getRecursiveListOfFiles(root).filter(item => extensions.exists(item.getName.endsWith(_))).map(item => item.getAbsolutePath).toList
  }

  def getRecursiveListOfFiles(dir: File): Array[File] = {
    val these = dir.listFiles
    these ++ these.filter(_.isDirectory).flatMap(getRecursiveListOfFiles)
  }

  def buildFilePath(folder: String, fileName: String): String = {
    s"$folder" + getDefault.getSeparator + fileName
  }
}
