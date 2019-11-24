package org.codecraftlabs.spark.utils

final case class Exceptions(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception(message, cause)