package sandbox

trait Exercise {
  def name: String

  def exec(): Unit

  def run(): Unit = {
    val horizontalRule = "------------------------------------------------------------------------"

    println(horizontalRule)
    println(name)
    println(horizontalRule)

    exec()

    println()
  }
}
