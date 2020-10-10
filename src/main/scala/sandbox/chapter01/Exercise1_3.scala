package sandbox.chapter01

import sandbox.Exercise

object Exercise1_3 extends Exercise {
  val name = "1.3 Exercise: Printable Library"

  // Scala provides a toString method to let us convert any value to a
  // String. However, this method comes with a few disadvantages: it is
  // implemented for every type in the language, many implementations are of
  // limited use, and we cant opt-in to specific implementations for specific
  // types.
  //
  // Lets define a Printable type class to work around these problems:

  // Define a type class Printable[A] containing a single method format.
  // format should accept a value of type A and return a String.
  trait Printable[A] {
    def format(value: A): String
  }

  // Create an object PrintableInstances containing instances of Printable
  // for String and Int.
  object PrintableInstances {
    implicit val string: Printable[String] =
      new Printable[String] {
        def format(value: String): String = value
      }

    implicit val int: Printable[Int] =
      new Printable[Int] {
        def format(value: Int): String = value.toString
      }
  }

  // Define an object Printable with two generic interface methods:

  object Printable {
    // format accepts a value of type A and a Printable of the corresponding
    // type. It uses the relevant Printable to convert the A to a String.
    def format[A](value: A)(implicit printable: Printable[A]): String = printable.format(value)

    // print accepts the same parameters as format and returns Unit. It prints
    // the formatted A value to the console using println.
    def print[A](value: A)(implicit printable: Printable[A]): Unit = println(format(value))
  }

  // Using the Library
  //
  // The code above forms a general purpose printing library that we can
  // use in multiple applications. Lets define an application now that uses
  // the library.

  // First well define a data type to represent a well-known type of
  // furry animal:
  final case class Cat(name: String, age: Int, color: String)

  // Next well create an implementation of Printable for Cat that
  // returns content in the following format:
  //   NAME is a AGE year-old COLOR cat.
  // Finally, use the type class on the console or in a short demo app:
  // create a Cat and print it to the console:
  object CatPrintableInstance {
    implicit val cat: Printable[Cat] =
      new Printable[Cat] {
        def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
      }
  }

  // Better Syntax
  //
  // Lets make our printing library easier to use by defining some
  // extension methods to provide better syntax:
  //
  // Create an object called PrintableSyntax.
  //
  // Use the extension methods to print the example Cat you created in the
  // previous exercise.
  object PrintableSyntax {

    // Inside PrintableSyntax define an implicit class PrintableOps[A] to
    // wrap up a value of type A.
    implicit class PrintableOps[A](value: A) {
      // In PrintableOps define the following methods:
      //   format accepts an implicit Printable[A] and returns a String
      //   representation of the wrapped A;
      def format(implicit printable: Printable[A]): String = printable.format(value)

      // print accepts an implicit Printable[A] and returns Unit. It prints the
      // wrapped A to the console.
      def print(implicit printable: Printable[A]): Unit = println(format)
    }
  }

  def exec(): Unit = {
    import PrintableInstances._

    Printable.print("foo")
    Printable.print(123)

    import CatPrintableInstance._

    Printable.print(Cat("Ron", 2, "brown"))

    import PrintableSyntax._

    "bar".print
    456.print
    Cat("Jon", 3, "orange").print
  }
}
