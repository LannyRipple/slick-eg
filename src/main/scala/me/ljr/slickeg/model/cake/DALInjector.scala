package me.ljr.slickeg.model.cake

import scala.slick.driver.JdbcDriver.backend

import me.ljr.slickeg.DBConf

trait DALInjector {

  val dbconf: DBConf

  val slickDriver: dbconf.slickDriver.type = dbconf.slickDriver

  val database: slickDriver.simple.Database = dbconf.database

  val jdbcDatabase: backend.Database = dbconf.jdbcDatabase
}
