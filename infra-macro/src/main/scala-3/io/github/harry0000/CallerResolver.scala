package io.github.harry0000

import scala.annotation.tailrec
import scala.quoted.*

object CallerResolver {

  inline def resolve: String = ${ resolveImpl }

  private def resolveImpl(using Quotes): Expr[String] = {
    import quotes.reflect.*

    @tailrec
    def findClassAndMethod(sym: Symbol, methods: Seq[Symbol] = Nil): (Symbol, Symbol) = {
      if (sym.isClassDef) {
        (sym, methods.head)
      } else if (sym.isDefDef) {
        findClassAndMethod(sym.owner, sym +: methods)
      } else {
        findClassAndMethod(sym.owner, methods)
      }
    }

    val (classSym, methodSym) = findClassAndMethod(Symbol.spliceOwner)
    Expr(s"${classSym.name}#${methodSym.name}")
  }
}
