package io.github.harry0000

object Hello extends Greeting with App {
  println(greeting)
}

trait Greeting {
  lazy val greeting: String = "hello"
}
