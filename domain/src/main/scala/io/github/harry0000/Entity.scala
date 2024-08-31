package io.github.harry0000

case class Entity(id: Int, name: String)

trait EntityRepository {
  def list(): String
}
