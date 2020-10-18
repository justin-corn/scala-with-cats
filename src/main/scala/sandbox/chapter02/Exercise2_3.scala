package sandbox.chapter02

object Exercise2_3 {
  // We've seen a few examples of monoids but there are plenty more to be
  // found. Consider Boolean. How many monoids can you define for this
  // type? For each monoid, define the combine and empty operations and
  // convince yourself that the monoid laws hold. Use the following
  // definitions as a starting point:

  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  object Semigroup {
    def apply[A](implicit semigroup: Semigroup[A]) = semigroup
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) = monoid
  }

  object BooleanMonoidInstances {
    implicit val and: Monoid[Boolean] =
      new Monoid[Boolean] {
        def empty: Boolean = true
        def combine(a: Boolean, b: Boolean): Boolean = a && b
      }

    implicit val or: Monoid[Boolean] =
      new Monoid[Boolean] {
        def empty: Boolean = false
        def combine(a: Boolean, b: Boolean): Boolean = a || b
      }

    implicit val xor: Monoid[Boolean] =
      new Monoid[Boolean] {
        def empty: Boolean = false
        def combine(a: Boolean, b: Boolean): Boolean = (a && !b) || (!a && b)
      }

    implicit val xnor: Monoid[Boolean] =
      new Monoid[Boolean] {
        def empty: Boolean = true
        def combine(a: Boolean, b: Boolean): Boolean = (a || !b) && (!a || b)
      }
  }
}
