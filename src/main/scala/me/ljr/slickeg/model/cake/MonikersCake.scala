package me.ljr.slickeg.model
package cake

trait MonikersCake {
  self: DALInjector =>

  import dbconf.slickDriver.simple._

  val monikers = TableQuery[Monikers]

  implicit def monikerIdColumnType =
    MappedColumnType.base[MonikerId, Long](_.id, new MonikerId(_))

  class Monikers(tag: Tag) extends Table[Moniker](tag, "Monikers") {

    def id = column[MonikerId]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name", O.NotNull)

    def * = (id, name) <> (Moniker.tupled, Moniker.unapply)

    def uniqName = index("m_idxName", name)
  }
}
