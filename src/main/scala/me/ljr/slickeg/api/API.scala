package me.ljr.slickeg.api

import scala.language.postfixOps

import me.ljr.slickeg.query.QueryLib
import me.ljr.slickeg.model.DAL

class API(override protected val DAL: DAL) extends QueryLib(DAL) {

  import DAL.slickDriver.simple._

  private val db = DAL.database

  def emd52handlesViaPeople(emd5: Column[String], targetAuth: Column[String] = "twitter"): List[String] = {
    db withSession {implicit session =>
      val q = for {
        eh <- handlesFromMd5("email", emd5)
        p <- relatedPeople(eh)
        h <- relatedHandles(p)
                if knownHandlesByAuth(h, targetAuth)
      } yield h.handle

      q.list()
    }
  }
}
