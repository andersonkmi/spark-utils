package org.codecraftlabs.spark.utils

import scala.collection.mutable

object Timer {
  private val timings = mutable.ArrayBuffer[String]()

  def timed[T](label: String, code: => T): T = {
    val start = System.currentTimeMillis()
    val result = code
    val stop = System.currentTimeMillis()
    timings += s"Processing $label took ${stop - start} ms."
    result
  }

  def timing: String = {
    val buffer = new StringBuilder
    timings.foreach(buffer.append(_).append("\n"))
    buffer.toString
  }
}
