package com.spark.starter.wordcount

import com.spark.starter.TestSparkProvider.fromCp
import com.spark.starter.{TestSparkProvider, UnitSpec}

/**
  * Created by songxiao on 9/23/17.
  */
class WordCountTest extends UnitSpec {

  behavior of "Word Count"

  it should "count word correctly" in {
    val wordCount = new WordCount(TestSparkProvider.sparkContext)
    val counts = wordCount.count(fromCp("wordcount/word_count.txt")).collect()

    counts.length shouldBe 3
    val countMap = counts.map(count => count._1 -> count._2).toMap
    countMap.apply("single") shouldBe 1
    countMap.apply("double") shouldBe 2
    countMap.apply("triple") shouldBe 3
  }

}
