package example.common

case class Coffee(
  id: Long = 0,
  name: String,
  supID: Long,
  price: Double,
  sales: Int,
  total: Int
)

object Rating extends Enumeration {
  val A: Rating.Value = Value("a")
  val B: Rating.Value = Value("b")
  val C: Rating.Value = Value("c")
  val D: Rating.Value = Value("d")
  val F: Rating.Value = Value("f")
}

case class Supplier(
  id: Long = 0,
  name: String,
  street: String,
  city: String,
  state: String,
  zip: String,
  rating: Rating.Value
)


