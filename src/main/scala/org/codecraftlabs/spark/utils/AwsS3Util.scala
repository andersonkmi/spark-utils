package org.codecraftlabs.spark.utils

import java.io.File.separator
import java.io.{File, FileInputStream, FileOutputStream}
import java.nio.file.Paths

import software.amazon.awssdk.core.sync.{RequestBody, ResponseTransformer}
import software.amazon.awssdk.services.s3.model.{GetObjectRequest, PutObjectRequest}
import org.apache.log4j.Logger
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

object AwsS3Util {
  private val S3Separator: String = "/"
  @transient lazy val logger:Logger = Logger.getLogger(getClass.getName)

  def downloadObject(region: String, bucket: String, key: String, local: String = "."): Unit = {
    val s3Client = S3Client.builder.region(Region.of(region)).build
    val objectRequest: GetObjectRequest = GetObjectRequest.builder.bucket(bucket).key(key).build
    val s3Object = s3Client.getObject(objectRequest)
    val tokens = key.split(S3Separator)

    val path = new File(local)
    val fos = new FileOutputStream(new File(path.getAbsolutePath + separator + tokens.last))
    val read_buf = new Array[Byte](1024)
    var len = s3Object.read(read_buf)
    while (len > 0) {
      fos.write(read_buf, 0, len)
      len = s3Object.read(read_buf)
    }
    s3Object.close()
    fos.close()
  }

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
