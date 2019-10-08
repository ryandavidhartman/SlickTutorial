package example

import java.util.concurrent.Executors

import slick.driver.H2Driver.api._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import TableQueries._

import scala.util.control.NonFatal

object HelloTables extends App {
  println("Start HelloTables Example!")

  val db = Database.forConfig("h2mem1")
  implicit val executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
  // or more commonly  import scala.concurrent.ExecutionContext.Implicits.global

  try {
    val resultsF = for {
      _ <- db.run(Actions.setup())
      allSuppliers <- db.run(suppliers.result)  //TableQuery.result => Slick's version of select *
    } yield allSuppliers

    val results = Await.result(resultsF, 2 seconds)

    println(s"Results: $results")
    println("End HelloTables Example!")
  } catch {
    case NonFatal(e) => println(s"Error in HelloTableExample: $e")
  } finally
 {
    db.close()
    System.exit(0)
  }
}
