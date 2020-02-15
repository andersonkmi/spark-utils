package org.codecraftlabs.spark.utils

final case class InvalidArgsException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)