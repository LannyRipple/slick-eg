package me.ljr.slickeg.model

trait PeopleHandlesMapCake {
  self: Profiled with PeopleCake with HandlesCake =>

  import profile.simple._

  val peopleHandlesMap = TableQuery[PeopleHandlesMap]

  class PeopleHandlesMap(tag: Tag) extends Table[(PersonId, HandleId)](tag, "PeopleHandles") {

    def pid = column[PersonId]("pid", O.NotNull)
    def hid = column[HandleId]("hid", O.NotNull)

    def peopleFK = foreignKey("People_FK", pid, people){_.id}
    def handlesFK = foreignKey("Handles_FK", hid, handles){_.id}

    def * = (pid, hid)

    def uniqPfkHfk = index("uniqPfkHfk", (pid, hid), unique = true)
    def indexPfk = index("idxPfk", pid)
    def indexHfk = index("idxHfk", hid)
  }
}
