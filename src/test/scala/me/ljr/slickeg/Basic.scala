package me.ljr.slickeg

import me.ljr.slickeg.model._

object Basic {

  val dbc = DBConf("scala.slick.driver.H2Driver", "org.h2.Driver", "jdbc:h2:mem:test", "test", "test")
  val db = dbc.database

  object DAL extends DAL(dbc)

  import DAL.profile.slickDriver.simple._

  def populateSchema() {
    db withSession { implicit sess =>
      DAL.ddl.create
    }
  }
}
