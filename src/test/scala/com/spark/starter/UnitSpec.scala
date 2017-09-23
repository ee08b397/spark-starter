package com.spark.starter

import org.apache.spark.SparkContext
import org.apache.spark.sql.hive.test.TestHive
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by songxiao on 9/23/17.
  */
abstract class UnitSpec extends FlatSpec with Matchers with MockFactory {

  // Sets Java System properties once for all tests
  import TestSetup.initialize

  initialize

}

object TestSparkProvider {

  System.setProperty("spark.default.parallelism", "2")
  System.setProperty("spark.sql.shuffle.partitions", "2")

  val hiveContext = TestHive
  val sparkContext: SparkContext = hiveContext.sparkContext

  def fromCp(s: String): String = this.getClass.getClassLoader.getResource(s).getFile

  sparkContext.setLogLevel("WARN")

}

object TestSetup {

  def initialize = evaluatesOnce

  val evaluatesOnce = {
    // Make Windows compatible with Mac/UNIX like systems line separators to pass the tests
    if (System.getProperty("os.name").startsWith("Windows")) {
      System.setProperty("line.separator", "\n")
      System.setProperty("hadoop.home.dir", "c:\\winutil\\")
    }
  }

}