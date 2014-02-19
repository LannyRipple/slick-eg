package me.ljr.slickeg.model
package cake

trait PeopleCake {
  self: DALInjector =>

  import dbconf.slickDriver.simple._

  val people = TableQuery[People]

  implicit def personIdColumnType =
    MappedColumnType.base[PersonId, Long](_.id, new PersonId(_))

  class People(tag: Tag) extends Table[Person](tag, "People") {

    def id = column[PersonId]("id", O.PrimaryKey, O.AutoInc)
    def guid = column[String]("guid", O.NotNull)

    def * = (id, guid) <> (Person.tupled, Person.unapply)

    def uniqGuid = index("p_uniqGuid", guid, unique = true)
  }
}
