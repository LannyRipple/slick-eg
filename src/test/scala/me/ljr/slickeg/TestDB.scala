package me.ljr.slickeg

import me.ljr.slickeg.model._
import me.ljr.slickeg.api.API

object TestDB {

  /*
   Start the server in a terminal with
      java -cp ~/.m2/repository/com/h2database/h2/1.3.174/h2-1.3.174.jar org.h2.tools.Shell

   using URL

      jdbc:h2:~/test;AUTO_SERVER=TRUE;DATABASE_TO_UPPER=FALSE

   ToDo - Figure out using jdbc:h2:mem:test;DATABASE_TO_UPPER=FALSE and connecting to it using Shell
          Docs say jdbc:h2:tcp://localhost/mem:test but can't seem to make it work.
   */
  val dbconf = DBConf(
    slickDriverName = "scala.slick.driver.H2Driver",
    jdbcDriverName = "org.h2.Driver",
    jdbcUrl =
      List(
        "jdbc:h2:~/test",
        "DATABASE_TO_UPPER=FALSE"
      ).mkString(";"),
    user = "sa",
    password = ""
  )

  object DAL extends DAL(dbconf)
  object API extends API(DAL)

  import DAL.slickDriver.simple._
  import DAL._

  def createSchema()(implicit session: Session) {
    ddl.create
  }

  def dropSchema()(implicit session: Session) {
    ddl.drop
  }

  def populatePeople()(implicit session: Session) {
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
  }

  def populateHandles()(implicit session: Session) {
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
  }

  def populateMonikers()(implicit session: Session) {
    def m(id: Long, name: String) = Moniker(new MonikerId(id), name)

    monikers.forceInsertAll(
      Seq(
        m(1, "fc-01")
      ): _*
    )
  }

  def populatePeopleHandles()(implicit session: Session) {
    def ph(pid: Long, hid: Long): (PersonId, HandleId) =
      (new PersonId(pid), new HandleId(hid))

    peopleHandlesMap ++= Seq(
      ph(1, 1),
      ph(1, 2),
      ph(2, 3),
      ph(3, 4)
    )
  }

  def populateHandlesMonikers()(implicit session: Session) {
    def hm(hid: Long, mid: Long): (HandleId, MonikerId) =
      (new HandleId(hid), new MonikerId(mid))

    handlesMonikersMap ++= Seq(
      hm(1, 1),
      hm(3, 1)
    )
  }

  def populateData()(implicit session: Session) {
    populatePeople()
    populateHandles()
    populateMonikers()
    populatePeopleHandles()
    populateHandlesMonikers()
  }
}
