package sandbox.chapter03

import cats.implicits._
import org.scalatest.funspec.AnyFunSpec
import sandbox.chapter03.Exercise3_5_4._

class Exercise3_5_4Spec extends AnyFunSpec {
  describe("tree functor") {
    it("should map Tree[Int]s") {
      val actual: Tree[Int] =
        Branch(
          Leaf(1),
          Branch(
            Leaf(2),
            Leaf(3)
          )
        )

      val expected: Tree[Int] =
        Branch(
          Leaf(11),
          Branch(
            Leaf(12),
            Leaf(13)
          )
        )

      assert(actual.map(_ + 10) === expected)
    }

    it("should map Tree[String]s") {
      val actual: Tree[String] =
        Branch(
          Leaf("foo"),
          Branch(
            Leaf("bar"),
            Leaf("baz")
          )
        )

      val expected: Tree[String] =
        Branch(
          Leaf("FOO"),
          Branch(
            Leaf("BAR"),
            Leaf("BAZ")
          )
        )

      assert(actual.map(_.toUpperCase) === expected)
    }
  }
}
