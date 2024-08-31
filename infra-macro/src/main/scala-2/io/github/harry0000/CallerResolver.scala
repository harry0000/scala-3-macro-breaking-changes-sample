package io.github.harry0000

import scala.annotation.tailrec
import scala.reflect.macros.blackbox

object CallerResolver {

  def resolve[A: c.WeakTypeTag](c: blackbox.Context): c.Expr[A] = {
    @tailrec
    def findClassAndMethod(sym: c.Symbol, methods: Seq[c.Symbol] = Nil): (c.Symbol, c.Symbol) = {
      if (sym.isClass) {
        (sym, methods.head)
      } else if (sym.isMethod) {
        findClassAndMethod(sym.owner, sym +: methods)
      } else {
        findClassAndMethod(sym.owner, methods)
      }
    }

    val (classSym, methodSym) = findClassAndMethod(c.internal.enclosingOwner)
    c.Expr(c.parse(s""""${classSym.name}#${methodSym.name}""""))
  }

}
