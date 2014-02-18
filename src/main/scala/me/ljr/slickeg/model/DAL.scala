package me.ljr.slickeg.model

import me.ljr.slickeg.DBConf

class DAL(override val dbconf: DBConf) extends DALInjector
  with PeopleCake
  with HandlesCake
  with MonikersCake
  with PeopleHandlesMapCake
  with HandlesMonikersMapCake {

  import dbconf.slickDriver.simple._

  val slickDriver: dbconf.slickDriver.type = dbconf.slickDriver

  val ddl: dbconf.slickDriver.type#DDL =
    people.ddl ++ handles.ddl ++ monikers.ddl ++ peopleHandlesMap.ddl ++ handlesMonikersMap.ddl

  val db: Database = dbconf.database
}
