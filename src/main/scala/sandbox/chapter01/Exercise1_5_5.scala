package sandbox.chapter01

import cats._
import cats.implicits._
import sandbox.Exercise

object Exercise1_5_5 extends Exercise {
  val name = "1.5.5 Exercise: Equality, Liberty, and Felinity"

  // Implement an instance of Eq for our running Cat example:
  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    implicit val catEq: Eq[Cat] = Eq.instance[Cat]((c1: Cat, c2: Cat) => c1 == c2)
  }

  def exec(): Unit =  {
    // Use this to compare the following pairs of objects for equality and inequality:

    val cat1 = Cat("Garfield",   38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")

    println(cat1 === cat1)
    println(cat2 === cat2)
    println(cat1 === cat2)

    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    println(optionCat1 === optionCat1)
    println(optionCat2 === optionCat2)
    println(optionCat1 === optionCat2)
  }
}
