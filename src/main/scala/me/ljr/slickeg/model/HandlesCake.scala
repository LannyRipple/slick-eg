package me.ljr.slickeg.model

trait HandlesCake {
  self: DALInjector =>

  import dbconf.slickDriver.simple._

  val handles = TableQuery[Handles]

  implicit val handleIdColumnType =
    MappedColumnType.base[HandleId, Long](_.id, new HandleId(_))

  class Handles(tag: Tag) extends Table[Handle](tag, "Handles") {

    def id = column[HandleId]("id", O.PrimaryKey, O.AutoInc)
    def auth = column[String]("auth", O.NotNull)
    def md5 = column[String]("md5", O.NotNull)
    def handle = column[String]("handle", O.Nullable)

    def * = (id, auth, md5, handle.?) <> (Handle.tupled, Handle.unapply)

    def uniqAuthMd5 = index("h_uniqAuthMd5", (auth, md5), unique = true)
    def uniqAuthHandle = index("h_uniqAuthHandle", (auth, handle), unique = true)
    def idxHandle = index("h_idxHandle", handle)
  }
}

class HandleId(val id: Long) extends AnyVal

case class Handle(id: HandleId, auth: String, md5: String, handle: Option[String])
