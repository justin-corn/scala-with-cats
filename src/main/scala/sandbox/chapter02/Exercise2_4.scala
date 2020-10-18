package sandbox.chapter02

import Exercise2_3.{Monoid, Semigroup}

object Exercise2_4 {
  object SetMonoidInstances {
    implicit def union[A]: Monoid[Set[A]] =
      new Monoid[Set[A]] {
        def empty = Set.empty[A]
        def combine(a: Set[A], b: Set[A]) = a union b
      }

    implicit def symDiff[A]: Monoid[Set[A]] =
      new Monoid[Set[A]] {
        def empty = Set.empty[A]
        def combine(a: Set[A], b: Set[A]) = (a diff b) union (b diff a)
      }
  }

  object SetSemigroupInstances {
    implicit def intersect[A]: Semigroup[Set[A]] =
      new Semigroup[Set[A]] {
        def combine(a: Set[A], b: Set[A]) = a intersect b
      }
  }
}
