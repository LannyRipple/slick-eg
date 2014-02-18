package me.ljr.slickeg.model

trait PeopleHandlesMapCake {
  self: DALInjector with PeopleCake with HandlesCake =>

  import dbconf.slickDriver.simple._

  val peopleHandlesMap = TableQuery[PeopleHandlesMap]

  class PeopleHandlesMap(tag: Tag) extends Table[(PersonId, HandleId)](tag, "PeopleHandles") {

    def pid = column[PersonId]("pid", O.NotNull)
    def hid = column[HandleId]("hid", O.NotNull)

    def * = (pid, hid)

    // Constraints
    def peopleFK = foreignKey("phm_pfk", pid, people){_.id}
    def handlesFK = foreignKey("phm_hfk", hid, handles){_.id}
    def uniqPfkHfk = index("phm_uniqPfkHfk", (pid, hid), unique = true)
    def indexPfk = index("phm_idxPfk", pid)
    def indexHfk = index("phm_idxHfk", hid)
  }
}
