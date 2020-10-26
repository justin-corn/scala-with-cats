package sandbox.chapter03

// 3.5.5.1 Exercise: Showing off with Contramap
object Exercise3_5_5_1 {

  // Implement the contramap method for Printable above. Start with the
  // following code template and replace the ??? with a working method
  // body:
  trait Printable[A] { self =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      new Printable[B] {
        def format(value: B): String =
          self.format(func(value))
      }
  }

  // For testing purposes, let’s define some instances of Printable for String and
  // Boolean:
  object PrintableInstances {
    implicit val stringPrintable: Printable[String] =
      new Printable[String] {
        def format(value: String): String =
          s"'${value}'"
      }

    implicit val booleanPrintable: Printable[Boolean] =
      new Printable[Boolean] {
        def format(value: Boolean): String =
          if (value) "yes" else "no"
      }
  }

  object PrintableSyntax {
    implicit class PrintableOps[A](src: A) {
      def format(implicit p: Printable[A]): String =
        p.format(src)

      def contramap[B](func: B => A)(implicit p: Printable[A]): Printable[B] =
        p.contramap(func)
    }
  }

  // Now define an instance of Printable for the following Box case
  // class. You’ll need to write this as an implicit def as described
  // in Section 1.2.3:

  final case class Box[A](value: A)

  // Rather than writing out the complete definition from scratch (new
  // Printable[Box] etc…), create your instance from an existing
  // instance using contramap.

  //  Your instance should work as follows:
  //  format(Box("hello world"))
  //    // res4: String = "'hello world'"
  //  format(Box(true))
  //    // res5: String = "yes"
  //  If we don’t have a Printable for the type inside the Box, calls to format should fail to compile:
  //  format(Box(123))
  //    // error: could not find implicit value for parameter p: repl.Session.App1.Printable[repl.Session.App1.Box[Int]]
  //    //       def encode(value: B): String =
  //    //                 ^

  object Box {
    def format[A](boxedValue: Box[A])(implicit p: Printable[A]): String =
      p.format(boxedValue.value)
  }
}
