package sandbox.chapter01

import cats._
import cats.implicits._
import sandbox.Exercise

object Exercise1_4_6 extends Exercise {
  val name = "1.4.6 Exercise: Cat Show"

  // Re-implement the Cat application from the previous section using Show
  // instead of Printable.

  final case class Cat(name: String, age: Int, color: String)

  object Cat {
    implicit val catShow: Show[Cat] =
      Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat.")
  }

  def exec(): Unit = {
    import Cat._
    println(Cat("Jan", 1, "black").show)
  }
}
