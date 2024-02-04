package org.codecraftlabs.spark.utils

import java.io.File
import java.nio.file.FileSystems.getDefault

object FileUtil {
  def getListOfFiles(dir: Option[String], extensions: List[String]): List[String] = {
    dir match {
      case Some(directory) => val root = new File (directory)
        if (! (root.exists && root.isDirectory) ) List[String]()
          else
            getRecursiveListOfFiles (root).filter (item => extensions.exists (item.getName.endsWith (_) ) ).map (item => item.getAbsolutePath).toList
      case None => List[String]()
    }
  }

  def buildFilePath(folder: String, fileName: String): String = {
    s"$folder" + getDefault.getSeparator + fileName
  }

  private def getRecursiveListOfFiles(dir: File): Array[File] = {
    val these = dir.listFiles
    these ++ these.filter(_.isDirectory).flatMap(getRecursiveListOfFiles)
  }

}
