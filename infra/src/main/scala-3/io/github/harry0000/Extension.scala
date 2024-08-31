package io.github.harry0000

import scala.language.implicitConversions

object Extension {
  class TableExtensionEx[E](table: Table[E]) extends TableExtension[E](table) {
    override def result: String = CallerResolver.resolve
  }

  implicit def tableToTableExtension[E](table: Table[E]): TableExtensionEx[E] =
    new TableExtensionEx[E](table)
}
