package sandbox.chapter02

import org.scalatest.funspec.AnyFunSpec
import sandbox.chapter02.Exercise2_3.{Monoid, Semigroup}
import sandbox.chapter02.Exercise2_4.{SetMonoidInstances, SetSemigroupInstances}

class Exercise2_4Spec extends AnyFunSpec {
  def combine[A: Semigroup](a: A, b: A): A = Semigroup[A].combine(a, b)

  // In addition to providing the combine and empty operations,
  // monoids must formally obey several laws. For all values x, y,
  // and z, in A, combine must be associative and empty must be an
  // identity element:
  def associativeLaw[A](x: A, y: A, z: A)(implicit m: Semigroup[A]): Boolean =
    m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

  def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean =
    (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)

  def assertAssociativeLaw[A: Semigroup](allValues: Set[A]): Unit =
    for {
      a <- allValues
      b <- allValues
      c <- allValues
    } {
      assert(associativeLaw(a, b, c), s"$a, $b, $c")
    }

  def assertIdentityLaw[A: Monoid](allValues: Set[A]): Unit =
    for (x <- allValues) {
      assert(identityLaw(x), s"$x")
    }

  val allSets = Set(
    Set.empty[String],
    Set("a"),
    Set("a", "b"),
    Set("a", "b", "c"),
    Set("a", "b", "c", "d"),
  )

  describe("set monoid instances") {
    describe("union") {
      import SetMonoidInstances.union

      implicit val strSetUnion = Monoid[Set[String]]

      it("should union sets") {
        assert(combine(Set.empty[String], Set.empty[String]) === Set.empty[String])
        assert(combine(Set("a"), Set.empty[String]) === Set("a"))
        assert(combine(Set.empty[String], Set("a")) === Set("a"))
        assert(combine(Set("a"), Set("b")) === Set("a", "b"))
      }

      it("should have an empty value") {
        assert(Monoid[Set[String]].empty === Set.empty[String])
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allSets)
      }

      it("should obey identity law") {
        assertIdentityLaw(allSets)
      }
    }

    describe("symmetric diff") {
      import SetMonoidInstances.symDiff

      implicit val strSetSymDiff = Monoid[Set[String]]

      it("should symmetric diff sets") {
        assert(combine(Set.empty[String], Set.empty[String]) === Set.empty[String])
        assert(combine(Set("a"), Set.empty[String]) === Set("a"))
        assert(combine(Set.empty[String], Set("a")) === Set("a"))
        assert(combine(Set("a"), Set("b")) === Set("a", "b"))
        assert(combine(Set("a", "b"), Set("b", "c")) === Set("a", "c"))
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allSets)
      }

      it("should obey identity law") {
        assertIdentityLaw(allSets)
      }
    }
  }

  describe("set semigroup instances") {
    describe("intersect") {
      import SetSemigroupInstances.intersect

      implicit val strSetIntersect = Semigroup[Set[String]]

      it("should intersect sets") {
        assert(combine(Set.empty[String], Set.empty[String]) === Set.empty[String])
        assert(combine(Set("a"), Set.empty[String]) === Set.empty[String])
        assert(combine(Set.empty[String], Set("a")) === Set.empty[String])
        assert(combine(Set("a"), Set("b")) === Set.empty[String])
        assert(combine(Set("a", "b"), Set("a")) === Set("a"))
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allSets)
      }
    }
  }
}
