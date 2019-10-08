package example

case class Coffee(
  id: Long = 0,
  name: String,
  supID: Long,
  price: Double,
  sales: Int,
  total: Int
)

class Supplier(
  val id: Long = 0,
  val name: String,
  val street: String,
  val city: String,
  val state: String,
  val zip: String,
  val rating: Double) {

  override def toString: String = s"""Supplier($id, $name, $street, $city, $state, $zip, $rating)"""
}


object Supplier {
  def apply(id: Long = 0, name: String, street: String, city: String, state: String, zip: String, rating: Double): Supplier
  = new Supplier(id, name, street, city, state, zip, rating)

  def unapply(s: Supplier): Option[(Long, String, String, String, String, String, Double)] =
    Option((s.id, s.name, s.street, s.city, s.state, s.zip, s.rating))

  def tupled(tuple: (Long, String, String, String, String, String, Double)): Supplier =  tuple match {
    case (x1, x2, x3, x4, x5, x6, x7) => apply(x1, x2, x3, x4, x5, x6, x7)
  }

}

/*
This is the easiest way
object Supplier {
  def createSupplier(fromDb: (Long, String, String, String, String, String, Double)): Supplier =  fromDb match {
    case Tuple7(x1, x2, x3, x4, x5, x6, x7) => new Supplier(x1, x2, x3, x4, x5, x6, x7)
  }

  def extractSupplier: Supplier => Option[(Long, String, String, String, String, String, Double)] =
    s => Option((s.id, s.name, s.street, s.city, s.state, s.zip, s.rating))
}
 */


