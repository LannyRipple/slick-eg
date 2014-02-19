package me.ljr.slickeg

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
    if (debug && jdbcUrl.startsWith("jdbc:h2:mem")) {
      // Start the TCP server so we can connect from a terminal using org.h2.tools.Shell
      // with jdbcUrl "jdbc:h2:tcp://localhost/mem:test"
      //
      // See: http://h2-database.66688.n3.nabble.com/In-Memory-Database-and-TCP-Server-Queries-td4027147.html
      //
      // Alternative debugging tool is to put
      //
      //     org.h2.tools.Server.startWebServer(db.createConnection())
      //
      // in your code to open an H2 browser.
      org.h2.tools.Server.createTcpServer().start()
    }

    Database.forURL(
      jdbcUrl,
      driver = jdbcDriverName,
      user = user,
      password = password
    )
  }
}
