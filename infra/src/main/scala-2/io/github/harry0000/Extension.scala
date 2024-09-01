package io.github.harry0000

import scala.language.implicitConversions

trait ExtensionBase {
  implicit def tableToTableExtension[E](table: Table[E]): TableExtension[E] =
    new TableExtension[E](table)
}

trait Extension { this: ExtensionBase =>
  class TableExtensionEx[E](
    table: Table[E]
  )(implicit enclosing: sourcecode.Enclosing) extends TableExtension[E](table) {
    override def result: String = enclosing.value
  }

  implicit def tableToTableExtensionEx[E](
    table: Table[E]
  )(implicit enclosing: sourcecode.Enclosing): TableExtensionEx[E] =
    new TableExtensionEx[E](table)
}
