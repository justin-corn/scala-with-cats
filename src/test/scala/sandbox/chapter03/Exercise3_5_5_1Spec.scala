package sandbox.chapter03

import org.scalatest.funspec.AnyFunSpec
import sandbox.chapter03.Exercise3_5_5_1._

class Exercise3_5_5_1Spec extends AnyFunSpec {
  describe("Printable") {
    import PrintableInstances._
    import PrintableSyntax._

    describe("Strings") {
      it("should format") {
        // can't figure out how to override scala.collection.immutable.StringOps syntax
        assert(stringPrintable.format("") === "''")
        assert(stringPrintable.format("foo") === "'foo'")
      }

      it("should contramap") {
        implicit val intPrintable: Printable[Int] =
          stringPrintable.contramap("the integer " + _.toString)

        assert(4.format === "'the integer 4'")
        assert(102.format === "'the integer 102'")
      }
    }

    describe("Booleans") {
      it("should format") {
        assert(true.format === "yes")
        assert(false.format === "no")
      }

      it("should contramap") {
        implicit val optIntPrintable: Printable[Option[Int]] =
          booleanPrintable.contramap(_.isDefined)

        assert((Some(1): Option[Int]).format === "yes")
        assert((None: Option[Int]).format === "no")
      }
    }

    describe("Box") {
      import Box.format

      it("should format Strings") {
        assert(format(Box("hello world")) === "'hello world'")
      }

      it("should format Booleans") {
        assert(format(Box(true)) === "yes")
      }

      it("should fail to format Ints") {
        // If we don’t have a Printable for the type inside the Box,
        // calls to format should fail to compile:
        //   format(Box(123))
        succeed
      }
    }
  }
}
