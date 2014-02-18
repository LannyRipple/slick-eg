package me.ljr.slickeg.api

import scala.language.postfixOps

import me.ljr.slickeg.model._
import me.ljr.slickeg.query.QueryLib

class API(override val DAL: DAL) extends QueryLib(DAL) {

  import DAL._
  import DAL.profile.slickDriver.simple._

  def getHandlesFromEmd5ViaPeople(emd5: Column[String], auth: Column[String]): List[String] = {
    db() withSession {implicit session =>
      val q = for {
        eh <- handlesFromMd5("email", emd5)
        th <- relatedHandlesViaPeople(eh)
                if handlesByAuth("twitter")(th)
      } yield th.handle

      q.list()
    }
  }
}
