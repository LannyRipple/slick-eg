package me.ljr.slickeg.model

trait PeopleCake {
  self: Profiled =>

  import profile.simple._

  val people = TableQuery[People]

  implicit val personIdColumnType =
    MappedColumnType.base[PersonId, Long](_.id, new PersonId(_))

  class People(tag: Tag) extends Table[Person](tag, "People") {

    def id = column[PersonId]("id", O.PrimaryKey, O.AutoInc)
    def guid = column[String]("guid", O.NotNull)

    def * = (id, guid) <> (Person.tupled, Person.unapply)

    def uniqGuid = index("uniqGuid", guid, unique = true)
  }
}

class PersonId(val id: Long) extends AnyVal

case class Person(id: PersonId, guid: String)
