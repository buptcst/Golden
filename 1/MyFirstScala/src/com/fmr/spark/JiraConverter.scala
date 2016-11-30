package com.fmr.spark
import org.apache.spark.{ SparkConf, SparkContext }
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature
import play.api.libs.json._

case class Person(name: String, lovesPandas: Boolean) // Note: must be a top level class
case class Issue(issueId:String, project:String, team:String){
  override def toString:String = "Issue [issueId=" + issueId + ", project=" + project + ", team=" + team + "]"
}
object JiraConverter {
  def main(args: Array[String]) {
    // var issue = new Issue
    //println(issue.toString)

    val conf = new SparkConf()
    conf.setAppName("JiraConverter")
    conf.setMaster("local[2]")

    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\TestFiles\\pandainfo.json", 2)

    val result = input.mapPartitions(records => {
        // mapper object created on each executor node
        val mapper = new ObjectMapper
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.registerModule(DefaultScalaModule)
        // We use flatMap to handle errors
        // by returning an empty list (None) if we encounter an issue and a
        // list with one element if everything is ok (Some(_)).
        records.flatMap(record => {
          try {
            Some(mapper.readValue(record, classOf[Person]))
          } catch {
            case e: Exception => None
          }
        })
    }, true)
       result.filter(_.lovesPandas).mapPartitions(records => {
      val mapper = new ObjectMapper
      mapper.registerModule(DefaultScalaModule)
      records.map(mapper.writeValueAsString(_))
    })
      .saveAsTextFile("C:\\TestFiles\\output")
      
      
      
        val parsed = input.map(Json.parse)
  implicit val personReads = Json.format[Issue]
 
val result1 = parsed.flatMap(record => personReads.reads(record).asOpt)
result1.collect
    }


  
}