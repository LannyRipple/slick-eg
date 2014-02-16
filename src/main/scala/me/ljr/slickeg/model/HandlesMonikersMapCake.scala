package me.ljr.slickeg.model

trait HandlesMonikersMapCake {
  self: DALInjector with HandlesCake with MonikersCake =>

  import profile.slickDriver.simple._

  val handlesMonikersMap = TableQuery[HandlesMonikersMap]

  class HandlesMonikersMap(tag: Tag) extends Table[(HandleId, MonikerId)](tag, "HandlesMonikers") {

    def hid = column[HandleId]("hid", O.NotNull)
    def mid = column[MonikerId]("mid", O.NotNull)

    def * = (hid, mid)

    // Constraints
    def handlesFK = foreignKey("hmm_hfk", hid, handles){_.id}
    def monikersFK = foreignKey("hmm_mfk", mid, monikers){_.id}
    def uniqHfkMfk = index("hmm_uniqHfkMfk", (hid, mid), unique = true)
    def idxHfk = index("hmm_idxHfk", hid)
    def idxMfk = index("hmm_idxMfk", mid)
  }
}
