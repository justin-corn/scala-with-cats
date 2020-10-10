package sandbox

import sandbox.chapter01._

object Main extends App {
  val exercises: Seq[Exercise] = Seq(
    Exercise1_3,
  )

  exercises.foreach(_.run)
}
