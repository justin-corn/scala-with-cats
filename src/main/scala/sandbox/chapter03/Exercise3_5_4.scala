package sandbox.chapter03

import cats._

object Exercise3_5_4 {
  // 3.5.4 Exercise: Branching out with Functors
  // Write a Functor for the following binary tree data type. Verify
  // that the code works as expected on instances of Branch and Leaf:

  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A])
    extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  object Tree {
    implicit val treeFunctor =
      new Functor[Tree] {
        def map[A, B](value: Tree[A])(fn: A => B): Tree[B] =
          value match {
            case Branch(l, r) =>
              Branch(map(l)(fn), map(r)(fn))

            case Leaf(v) =>
              Leaf(fn(v))
          }
      }
  }
}
