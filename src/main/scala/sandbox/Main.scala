package sandbox

import sandbox.chapter01._

object Main extends App {
  val ch1: Seq[Exercise] = Seq(
    Exercise1_3,
    Exercise1_4_6,
    Exercise1_5_5,
  )

  val toRun = ch1

  toRun.foreach(_.run)
}
