package me.ljr.slickeg.model

trait HandlesMonikersMapCake {
  self: Profiled with HandlesCake with MonikersCake =>

  import profile.simple._

  val peopleHandlesMap = TableQuery[HandlesMonikersMap]

  class HandlesMonikersMap(tag: Tag) extends Table[(HandleId, MonikerId)](tag, "HandlesMonikers") {

    def hid = column[HandleId]("hid", O.NotNull)
    def mid = column[MonikerId]("mid", O.NotNull)

    def handlesFK = foreignKey("Handles_FK", hid, handles){_.id}
    def monikersFK = foreignKey("Monikers_FK", mid, monikers){_.id}

    def * = (hid, mid)

    def uniqHfkMfk = index("uniqHfkMfk", (hid, mid), unique = true)
    def idxHfk = index("idxHfk", hid)
    def idxMfk = index("idxMfk", mid)
  }
}
