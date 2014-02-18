package me.ljr.slickeg

import me.ljr.slickeg.model._
import me.ljr.slickeg.api.API

object TestDB {

  val dbconf = DBConf("scala.slick.driver.H2Driver", "org.h2.Driver", "jdbc:h2:mem:test", user = "", password = "")

  object DAL extends DAL(dbconf)
  object API extends API(DAL)

  def populateSchema(DAL: DAL) {
    import DAL._
    import DAL.dbconf.slickDriver.simple._

    db withSession { implicit session =>
      ddl.create

      def p(id: Long, guid: String) = Person(new PersonId(id), guid)

      people.forceInsertAll(
        Seq(
          p(1, "lanny"),
          p(2, "dave"),
          p(3, "nathan"),
          p(4, "jeff"),
          p(5, "geoff"),
          p(6, "jason")
        ): _*
      )

      def h(id: Long, auth: String, md5: String, handle: Option[String] = None) =
        Handle(new HandleId(id), auth, md5, handle)

      handles.forceInsertAll(
        Seq(
          h(1, "email", "md5-lanny@spotright.com", Some("lanny@spotright.com")),
          h(2, "twitter", "md5-lannyripple", Some("lannyRipple")),
          h(3, "email", "md5-dave@spotright.com", None),
          h(4, "twitter", "md5-nhalko", Some("nhalko"))
        ): _*
      )

      def m(id: Long, name: String) = Moniker(new MonikerId(id), name)

      monikers.forceInsertAll(
        Seq(
          m(1, "fc-01")
        ): _*
      )

      def ph(pid: Long, hid: Long): (PersonId, HandleId) =
        (new PersonId(pid), new HandleId(hid))

      peopleHandlesMap ++= Seq(
        ph(1, 1),
        ph(1, 2),
        ph(2, 3),
        ph(3, 4)
      )

      def hm(hid: Long, mid: Long): (HandleId, MonikerId) =
        (new HandleId(hid), new MonikerId(mid))

      handlesMonikersMap ++= Seq(
        hm(1, 1),
        hm(3, 1)
      )
    }
  }
}
