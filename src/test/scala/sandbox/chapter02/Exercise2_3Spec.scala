package sandbox.chapter02

import org.scalatest.funspec.AnyFunSpec
import Exercise2_3.BooleanMonoidInstances
import Exercise2_3.Monoid

class Exercise2_3Spec extends AnyFunSpec {
  describe("boolean monoid instances") {
    val allBooleans = Set(true, false)

    def combine[A: Monoid](a: A, b: A) = Monoid[A].combine(a, b)

    // In addition to providing the combine and empty operations,
    // monoids must formally obey several laws. For all values x, y,
    // and z, in A, combine must be associative and empty must be an
    // identity element:
    def associativeLaw[A](x: A, y: A, z: A)(implicit m: Monoid[A]): Boolean =
      m.combine(x, m.combine(y, z)) == m.combine(m.combine(x, y), z)

    def identityLaw[A](x: A)(implicit m: Monoid[A]): Boolean =
      (m.combine(x, m.empty) == x) && (m.combine(m.empty, x) == x)

    def assertAssociativeLaw[A: Monoid](allValues: Set[A]): Unit =
      for {
        a <- allValues
        b <- allValues
        c <- allValues
      } {
        assert(associativeLaw(a, b, c))
      }

    def assertIdentityLaw[A: Monoid](allValues: Set[A]): Unit =
      for (x <- allValues) {
        assert(identityLaw(x))
      }

    describe("and") {
      import BooleanMonoidInstances.and

      it("should combine booleans") {
        assert(combine(true, true) === true)
        assert(combine(true, false) === false)
        assert(combine(false, true) === false)
        assert(combine(false, false) === false)
      }

      it("should have an empty value") {
        assert(Monoid[Boolean].empty === true)
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allBooleans)
      }

      it("should obey identity law") {
        assertIdentityLaw(allBooleans)
      }
    }

    describe("or") {
      import BooleanMonoidInstances.or

      it("should combine booleans") {
        assert(combine(true, true) === true)
        assert(combine(true, false) === true)
        assert(combine(false, true) === true)
        assert(combine(false, false) === false)
      }

      it("should have an empty value") {
        assert(Monoid[Boolean].empty === false)
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allBooleans)
      }

      it("should obey identity law") {
        assertIdentityLaw(allBooleans)
      }
    }

    describe("xor") {
      import BooleanMonoidInstances.xor

      it("should combine booleans") {
        assert(combine(true, true) === false)
        assert(combine(true, false) === true)
        assert(combine(false, true) === true)
        assert(combine(false, false) === false)
      }

      it("should have an empty value") {
        assert(Monoid[Boolean].empty === false)
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allBooleans)
      }

      it("should obey identity law") {
        assertIdentityLaw(allBooleans)
      }
    }

    describe("xnor") {
      import BooleanMonoidInstances.xnor

      it("should combine booleans") {
        assert(combine(true, true) === true)
        assert(combine(true, false) === false)
        assert(combine(false, true) === false)
        assert(combine(false, false) === true)
      }

      it("should have an empty value") {
        assert(Monoid[Boolean].empty === true)
      }

      it("should obey associativity law") {
        assertAssociativeLaw(allBooleans)
      }

      it("should obey identity law") {
        assertIdentityLaw(allBooleans)
      }
    }
  }
}
