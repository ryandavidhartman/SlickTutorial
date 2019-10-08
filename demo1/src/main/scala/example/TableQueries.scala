package example

import slick.lifted.TableQuery

object TableQueries {
  // The query interface for the Suppliers table
  lazy val suppliers: TableQuery[SupplierTable] = TableQuery[SupplierTable]

  // the query interface for the Coffees table
  val coffees: TableQuery[CoffeeTable] = TableQuery[CoffeeTable]

}
