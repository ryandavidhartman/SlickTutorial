package example

import java.util.concurrent.Executors

import slick.jdbc.H2Profile.api._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor}
import example.common.TableQueries._
import example.common.Actions

import scala.util.control.NonFatal

object HelloTables extends App {
  println("Start HelloTables Example!")

  val db = Database.forConfig("h2mem1")
  implicit val executionContext: ExecutionContextExecutor =
    ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
  // or more commonly  import scala.concurrent.ExecutionContext.Implicits.global

  try {
    val resultsF = for {
      _ <- db.run(Actions.setup())
      allSuppliers <- db.run(suppliers.result)  //TableQuery.result => Slick's version of `select *`
    } yield allSuppliers

    val results = Await.result(resultsF, 2 seconds)

    println(s"Suppliers:\n${results.mkString("\n")}\n")
    println("End HelloTables Example!")
  } catch {
    case NonFatal(e) => println(s"Error in HelloTableExample: $e")
  } finally
 {
    db.close()
    System.exit(0)
  }
}
