package com.fmr.spark
import org.apache.spark.{ SparkConf, SparkContext }
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.DeserializationFeature
import java.sql.DriverManager
import play.api.libs.json._
import scala.collection.mutable.ListBuffer
//import scala.util.parsing.json.JSON
case class Person(name: String, lovesPandas: String) // Note: must be a top level class
case class Issue(key: String, project: String, team: String) {
  override def toString: String = "Issue [issueId=" + key + ", project=" + project + ", team=" + team + "]"
}
object JiraConverter {
  def main(args: Array[String]) {
    // var issue = new Issue
    //println(issue.toString)

    val conf = new SparkConf()
    conf.setAppName("JiraConverter")
    conf.setMaster("local[2]")

    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\TestFiles\\pandainfo.json", 2).collect()
  //var unconfirmed = mutable.Set.empty[Person]
    val buf = new ListBuffer[Person]
    //var unconfirmed1 = mutable.Set.empty[String]
    //val result = 
   //    val a = Array(1,2,3)
      input.foreach { x => 
      {

        val json:JsValue= Json.parse(x)
        val name = (json \ "name").get.as[String]
        val panda = (json \ "lovesPandas").get.as[String]
        var person = new Person(name,panda)
        println(person.name)
        println(person.lovesPandas)
        buf+= person
     //   unconfirmed1 += person.name
        }
      }
    val list = buf.toList
    println(list.size)
  //      println(unconfirmed1.size)
    list.foreach { y => println(y.name) }
   // unconfirmed1.foreach { y => print(y) }
//    val result = input.mapPartitions(records => {
//      // mapper object created on each executor node
//      val mapper = new ObjectMapper
//      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//      mapper.registerModule(DefaultScalaModule)
//      // We use flatMap to handle errors
//      // by returning an empty list (None) if we encounter an issue and a
//      // list with one element if everything is ok (Some(_)).
//      records.foreach { x => x }
////      records.flatMap(record => {
////Some(JSON.parseFull(record) match {
////  case Some(map: Map[String, Any]) => println(map)  
////  case None => println("Parsing failed")  
////  case other => println("Unknown data structure: " + other) 
////}) 
////
////        try {
////          Some(mapper.readValue(record, classOf[Person]))
////        } catch {
////          case e: Exception => None
////        }
////      })
//    }, true)
    //result.filter(_.key).mapPartitions(records => {
//    result.mapPartitions(records => {
//      val mapper = new ObjectMapper
//      mapper.registerModule(DefaultScalaModule)
//      records.map(mapper.writeValueAsString(_))
//    })
    // .saveAsTextFile("C:\\TestFiles\\output1")

    //    result.foreachPartition { records =>
    //      records.foreach(record =>
    //        {
    //          Class.forName("oracle.jdbc.driver.OracleDriver").newInstance()
    //          val lda = DriverManager.getConnection("jdbc:oracle:thin:@10.168.104.92:1521:Orcl", "ryan", "ryan123")
    //          val sth = lda.createStatement()
    //          sth.execute("insert into sys.jira_issues (issue_id) values('"
    //            + record.key + "')")
    //          sth.close()
    //          lda.close()
    //        })
    //    }

  }

}