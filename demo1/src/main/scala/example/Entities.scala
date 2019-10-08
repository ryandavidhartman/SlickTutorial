package example

case class Coffee(
  id: Long = 0,
  name: String,
  supID: Long,
  price: Double,
  sales: Int,
  total: Int
)

case class Supplier(
  id: Long = 0,
  name: String,
  street: String,
  city: String,
  state: String,
  zip: String,
  rating: Double)


