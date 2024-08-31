package io.github.harry0000

import scala.language.experimental.macros
import scala.language.implicitConversions

object Extension {
  class TableExtensionEx[E](table: Table[E]) extends TableExtension[E](table) {
    override def result: String = macro CallerResolver.resolve[String]
  }

  implicit def tableToTableExtension[E](table: Table[E]): TableExtensionEx[E] =
    new TableExtensionEx[E](table)
}
