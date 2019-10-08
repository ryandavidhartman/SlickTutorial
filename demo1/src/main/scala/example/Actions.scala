package example

import slick.jdbc.H2Profile.api._

// Tables -> slick's notation of the DB table
// Queries -> slicks DSL for sql operations
// Actions -> "commands" to the DB (often just queries), their results comeback async (of course!)
// Finally we'll be a handle to the db to actually run our actions (db.run(...))

object Actions {

  import  TableQueries._

  val supplier1 = Supplier(1L, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199", 1.0)
  val supplier2 = Supplier(2L, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460", 2.0)
  val supplier3 = Supplier(3L, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966", 3.0)

  val coffee1 = Coffee(1, "Acme Special", 1, 1.00, 100, 2000)
  val coffee2 = Coffee(2, "Regular", 2, 0.50, 659, 20000)
  val coffee3 = Coffee(3, "Best Coffee", 2, 5.19, 46, 1000)


  //Here is a simple Slick DBIOAction that is a sequence of 3 actions.  One Action to create the
  // db schema and two Actions to insert some data
  def setup(): DBIOAction[Unit, NoStream, Nothing] = DBIO.seq(
    // Create the tables, including primary and foreign keys
    (suppliers.schema ++ coffees.schema).create,

    // Insert some suppliers
    suppliers ++= Seq(supplier1, supplier2, supplier3),

    // Insert some coffees (using JDBC's batch insert feature, if supported by the DB)
    coffees ++= Seq(coffee1, coffee2, coffee3)

  )

}
