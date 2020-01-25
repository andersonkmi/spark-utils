package org.codecraftlabs.spark.utils

import java.io.{File, FileInputStream}

import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import org.apache.log4j.Logger
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

object AwsS3Util {
  @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)

  def uploadObjects(region: String, bucket: String, prefix: String, localBasePath: String, files: List[String]): Unit = {
    val s3Client = S3Client.builder.region(Region.of(region)).build
    files.foreach(item => uploadSingleFile(s3Client, bucket, prefix + item.substring(localBasePath.length).replaceAll("\\\\", "/"), item))
  }

  private def uploadSingleFile(s3Client: S3Client, bucket: String, key: String, uploadFileName: String): Unit = {
    try {
      logger.info(s"Uploading file $uploadFileName to S3")
      val file = new File(uploadFileName)
      val is = new FileInputStream(file)
      val request = PutObjectRequest.builder.bucket(bucket).contentLength(file.length()).key(key).build()
      s3Client.putObject(request, RequestBody.fromInputStream(is, file.length))
      is.close()
    } catch {
      case exception: Exception => exception.printStackTrace()
    }
  }
}
