package io.github.harry0000

import scala.concurrent.ExecutionContext

class ExtensionSuite extends munit.FunSuite {
  implicit val ec: ExecutionContext = ExecutionContext.global

  class Repository extends EntityRepositoryImpl with ExtensionBase with Extension

  test("return caller class & method name") {
    val repository = new Repository()

    assertEquals(repository.list(), "io.github.harry0000.EntityRepositoryImpl#list")
  }
}
