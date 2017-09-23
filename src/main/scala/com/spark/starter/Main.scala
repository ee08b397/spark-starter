package com.spark.starter

import com.spark.starter.wordcount.WordCount
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by songxiao on 9/23/17.
  */
object Main extends App {

  println("Hello Spark!")

  val sparkConf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local")
      .set("spark.ui.port", "4080")

  val sparkContext = new SparkContext(sparkConf)
  sparkContext.setLogLevel("WARN")

  val wordCount = new WordCount(sparkContext)
  val counts = wordCount.count(from("wordcount/food.txt"))

  println(counts.collect().mkString(", \n"))
  sparkContext.stop

  def from(s: String): String = this.getClass.getClassLoader.getResource(s).getFile

}
