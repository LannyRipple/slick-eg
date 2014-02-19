package me.ljr.slickeg.model

import me.ljr.slickeg.DBConf
import me.ljr.slickeg.model.cake._

class DAL(override val dbconf: DBConf) extends DALInjector
  with PeopleCake
  with HandlesCake
  with MonikersCake
  with PeopleHandlesMapCake
  with HandlesMonikersMapCake {

  import slickDriver.simple._

  val ddl: dbconf.slickDriver.type#DDL =
    people.ddl ++ handles.ddl ++ monikers.ddl ++ peopleHandlesMap.ddl ++ handlesMonikersMap.ddl

}
