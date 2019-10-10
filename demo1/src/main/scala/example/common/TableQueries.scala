package example.common

import slick.lifted.TableQuery
import slick.jdbc.H2Profile.api._

object TableQueries {
  // The query interface for the Suppliers table.  This isn't a representation of the table,
  // rather it is the root of the query dsl for the table
  lazy val suppliers: TableQuery[SupplierTable] = TableQuery[SupplierTable]

  // the query interface for the Coffees table
  val coffees: TableQuery[CoffeeTable] = TableQuery[CoffeeTable]

  val selectAllQuery = suppliers  //This is the basic select all query

  val selectWhereQuery = suppliers  //select with a where clause
    .filter(_.state === "Indiana")

  val selectSortedQuery1 = suppliers  // select with a sort
      .sortBy(_.name.asc)

  val selectSortedQuery2 = suppliers  // another sort select
      .sortBy(s => (s.state.asc, s.city.asc))

  val selectPagedQuery = suppliers  //paged select
      .drop(2).take(1)

  val selectColumnsQuery1 = suppliers  //just select a single column
      .map(_.name)

  val selectColumnsQuery2 = suppliers  //select two columns
      .map(s => (s.name, s.state))

  val selectCombinedQuery = suppliers  //applying two sql operations
      .filter(_.state === "Indiana")
      .map(_.name)

  // Returning single/multiple results ----------

  val selectPagedAction1 = selectPagedQuery
      .result

  val selectPagedAction2 = selectPagedQuery
      .result
      .headOption

  val selectPagedAction3 = selectPagedQuery
      .result
      .head

}
