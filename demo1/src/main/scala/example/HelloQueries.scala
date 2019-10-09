package example

import java.util.concurrent.Executors

import slick.jdbc.H2Profile.api._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, ExecutionContextExecutor}
import TableQueries._

import scala.util.control.NonFatal

object HelloQueries extends App {
  println("Start HelloQueries Example!")

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
    println("End HelloQueries Example!")
  } catch {
    case NonFatal(e) => println(s"Error in HelloQueriesExample: $e")
  } finally
  {
    db.close()
    System.exit(0)
  }
}
