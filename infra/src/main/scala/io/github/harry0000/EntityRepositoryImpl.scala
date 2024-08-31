package io.github.harry0000

trait Table[E]
class TableExtension[E](private val table: Table[E]) {
  def result: String = ""
}

class EntityTable extends Table[Entity]

class EntityRepositoryImpl extends EntityRepository {
  import Extension.*

  private val table: EntityTable = new EntityTable

  def list(): String = table.result
}
