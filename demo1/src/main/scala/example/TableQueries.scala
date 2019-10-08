package example

import slick.lifted.TableQuery

object TableQueries {
  // The query interface for the Suppliers table.  This isn't a representation of the table,
  // rather it is the root of the query dsl for the table
  lazy val suppliers: TableQuery[SupplierTable] = TableQuery[SupplierTable]

  // the query interface for the Coffees table
  val coffees: TableQuery[CoffeeTable] = TableQuery[CoffeeTable]

}
