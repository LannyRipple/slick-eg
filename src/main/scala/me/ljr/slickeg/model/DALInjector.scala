package me.ljr.slickeg.model

import scala.slick.driver.JdbcProfile

trait DALInjector {

  val profile: DBConf
}

object DBConf {

  val slickDriverMap = Map(
    "scala.slick.driver.H2Driver" -> scala.slick.driver.H2Driver,
    "scala.slick.driver.PostgressDriver" -> scala.slick.driver.PostgresDriver,
    "scala.slick.driver.SqliteDriver" -> scala.slick.driver.SQLiteDriver
  )
}

case class DBConf(
  slickDriverName: String,
  jdbcDriverName: String,
  jdbcUrl: String,
  user: String,
  password: String) {

  Class.forName(jdbcDriverName)

  val slickDriver: JdbcProfile = DBConf.slickDriverMap(slickDriverName)

  import slickDriver.simple._

  def database: Database = Database.forURL(
    jdbcUrl,
    driver = jdbcDriverName,
    user = user,
    password = password
  )
}

