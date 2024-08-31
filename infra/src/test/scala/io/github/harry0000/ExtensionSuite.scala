package io.github.harry0000

import scala.concurrent.ExecutionContext

class ExtensionSuite extends munit.FunSuite {
  implicit val ec: ExecutionContext = ExecutionContext.global

  test("return caller class & method name") {
    val repository = new EntityRepositoryImpl()

    assertEquals(repository.list(), "EntityRepositoryImpl#list")
  }
}
