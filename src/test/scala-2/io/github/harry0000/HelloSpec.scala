package io.github.harry0000

class HelloSpec extends munit.FunSuite {
  test("say hello") {
    assertEquals(Hello.greeting, "hello")
  }
}
