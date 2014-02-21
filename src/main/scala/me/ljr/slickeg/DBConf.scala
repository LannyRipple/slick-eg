package me.ljr.slickeg

import scala.slick.driver.JdbcDriver.backend
import scala.slick.driver.JdbcProfile

object DBConf {

  val slickDriverMap = Map(
    // jdbc driver = "org.h2.Driver"; jdbc memory url = "jdbc:h2:mem:$dbname"
    "scala.slick.driver.H2Driver" -> scala.slick.driver.H2Driver,

    // jdbcDriver = "org.postgresql.Driver"
    "scala.slick.driver.PostgressDriver" -> scala.slick.driver.PostgresDriver,

    // jdbcDriver = "org.sqlite.JDBC"; jdbc memory url = "jdbc:sqlite:memory"
    "scala.slick.driver.SqliteDriver" -> scala.slick.driver.SQLiteDriver
  )
}

case class DBConf(
                   slickDriverName: String,
                   jdbcDriverName: String,
                   jdbcUrl: String,
                   user: String,
                   password: String,
                   debug: Boolean = false) {

  require(DBConf.slickDriverMap.contains(slickDriverName), "DAL does not support this slick driver")

  // Load the jdbcDriver class
  Class.forName(jdbcDriverName)

  val slickDriver: JdbcProfile = DBConf.slickDriverMap(slickDriverName)

  import slickDriver.simple._

  /**
   * Get a database connection.
   *
   * This could be replaced with a connection pool.
   * @see com.spotright.models.slick.BoneCPConfigBuilder
   */
  lazy val database: Database = {
    Database.forURL(
      jdbcUrl,
      driver = jdbcDriverName,
      user = user,
      password = password
    )
  }

  lazy val jdbcDatabase: backend.Database =
    backend.Database.forURL(
      jdbcUrl,
      driver = jdbcDriverName,
      user = user,
      password = password
    )
}
