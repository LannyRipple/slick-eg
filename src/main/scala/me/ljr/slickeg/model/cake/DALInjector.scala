package me.ljr.slickeg.model.cake

import me.ljr.slickeg.DBConf

trait DALInjector {

  val dbconf: DBConf

  val slickDriver: dbconf.slickDriver.type = dbconf.slickDriver

  val database: slickDriver.simple.Database = dbconf.database
}
