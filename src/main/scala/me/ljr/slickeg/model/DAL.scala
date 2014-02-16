package me.ljr.slickeg.model

class DAL(val profile: DBConf) extends DALInjector
  with PeopleCake
  with HandlesCake
  with MonikersCake
  with PeopleHandlesMapCake
  with HandlesMonikersMapCake {

  import profile.slickDriver.simple._

  val ddl =  people.ddl ++ handles.ddl ++ monikers.ddl ++ peopleHandlesMap.ddl ++ handlesMonikersMap.ddl

}
