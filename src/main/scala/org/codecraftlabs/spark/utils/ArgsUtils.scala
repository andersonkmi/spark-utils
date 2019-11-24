package org.codecraftlabs.spark.utils

import scala.collection.mutable

object ArgsUtils {
  private val ArgsKeyIndicator: String = "--"

  @throws(classOf[InvalidArgsException])
  def parseArgs(args: Array[String]): Map[String, String] = {
    val result = mutable.Map[String, String]()

    if (args == null) {
      result.toMap
    } else {
      var currentKey = ""
      for(index <- args.indices) {
        val currentItem = args(index)
        if(currentItem.startsWith(ArgsKeyIndicator)) {
          currentKey = currentItem
          result(currentKey) = ""
        } else {
          result(currentKey) = currentItem
        }
      }
      validateParsedArgs(result.toMap)
      result.toMap
    }
  }

  @throws(classOf[InvalidArgsException])
  private def validateParsedArgs(args: Map[String, String]): Unit = {
    if(args.exists(_._2.isEmpty)) throw InvalidArgsException("Missing arguments")
  }
}