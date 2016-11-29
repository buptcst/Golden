package com.fmr.spark
import org.apache.spark.{ SparkConf, SparkContext }
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature
object JiraConverter {
  def main(args: Array[String]) {
    // var issue = new Issue
    //println(issue.toString)

    val conf = new SparkConf()
    conf.setAppName("JiraConverter")
    conf.setMaster("local[2]")

    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\TestFiles\\test.txt", 2)

    val result = input.flatMap(record => {
      try {
        Some(mapper.readValue(record, classOf[Issue]))
      }catch{
        case e:Exception => None
      }

    })
  }
}