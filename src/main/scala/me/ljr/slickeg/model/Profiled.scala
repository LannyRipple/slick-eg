package me.ljr.slickeg.model

import scala.slick.driver.JdbcProfile

trait Profiled {

  val profile: JdbcProfile
}
