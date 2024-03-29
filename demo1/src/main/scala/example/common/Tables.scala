package example.common

import slick.jdbc.H2Profile.api._
import slick.lifted.{ForeignKeyQuery, ProvenShape}


class SupplierTable(tag: Tag) extends Table[Supplier](tag, "SUPPLIERS") {

  // This is the primary key column:
  def id: Rep[Long] = column[Long]("SUP_ID", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("SUP_NAME")
  def street: Rep[String] = column[String]("STREET")
  def city: Rep[String] = column[String]("CITY")
  def state: Rep[String] = column[String]("STATE")
  def zip: Rep[String] = column[String]("ZIP")
  def rating: Rep[Rating.Value] = column[Rating.Value]("RATING")

  implicit val columnType: BaseColumnType[Rating.Value] = MappedColumnType.base[Rating.Value, String](_.toString, Rating.withName)

  // Every table needs a * projection with the same type as the table's type parameter
  def * :ProvenShape[Supplier] = (id, name, street, city, state, zip, rating) <> (Supplier.tupled, Supplier.unapply)
}

class CoffeeTable(tag: Tag) extends Table[Coffee](tag, "COFFEES") {

  def id: Rep[Long] = column[Long]("COF_ID", O.PrimaryKey, O.AutoInc)
  def name: Rep[String] = column[String]("COF_NAME")
  def supID: Rep[Long] = column[Long]("SUP_ID")
  def price: Rep[Double] = column[Double]("PRICE")
  def sales: Rep[Int] = column[Int]("SALES")
  def total: Rep[Int] = column[Int]("TOTAL")

  def * : ProvenShape[Coffee] = (id, name, supID, price, sales, total) <> (Coffee.tupled, Coffee.unapply )

  // A reified foreign key relation that can be navigated to create a join
 def supplier: ForeignKeyQuery[SupplierTable, Supplier] =  foreignKey("SUP_FK", supID, TableQuery[SupplierTable])(_.id)
}
