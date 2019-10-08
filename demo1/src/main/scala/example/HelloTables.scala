package example

import java.util.concurrent.Executors

import slick.driver.H2Driver.api._

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object HelloTables extends App {

  override def main(args: Array[String]): Unit = {
    import TableQueries._

    println("Start HelloTables Example!")

    val db = Database.forConfig("h2mem1")
    implicit val executionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(4))
    // or more commonly  import scala.concurrent.ExecutionContext.Implicits.global

    val resultsF = for {
      _ <- db.run(DBHelpers.setup())
      count <- db.run(suppliers.result)
    } yield count

    val results = Await.result(resultsF, 2 seconds)

    println(s"Results: $results")
    println("End HelloTables Example!")

  }

}
