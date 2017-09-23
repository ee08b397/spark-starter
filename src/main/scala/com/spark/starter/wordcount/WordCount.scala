package com.spark.starter.wordcount

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by songxiao on 9/23/17.
  */
class WordCount(sparkContext: SparkContext) {

  def count(path: String): RDD[(String, Int)] = {
    val text: RDD[String] = sparkContext.textFile(path)
    val counts = text.flatMap(_.split(" "))
        .map(word => (word, 1))
        .reduceByKey(_ + _)

    counts
  }

}
