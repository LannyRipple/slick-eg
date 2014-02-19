package me.ljr.slickeg.api

import scala.language.postfixOps

import me.ljr.slickeg.query.QueryLib
import me.ljr.slickeg.model.DAL

class API(override protected val DAL: DAL) extends QueryLib(DAL) {

  import DAL.slickDriver.simple._

  private val db = DAL.database

  // Slick 1.0 way.  Still supported.
  def slick10_emd52handlesViaPeopleCompiled =
    for {
      (emd5, targetAuth) <- Parameters[(String, String)]
      eh <- handlesFromMd5("email", emd5)
      p <- relatedPeople(eh)
      h <- relatedHandles(p)
              if h.auth === targetAuth && h.handle.isNotNull
    } yield h.handle

  // Slick 2.0 way.
  val emd52handlesViaPeopleCompiled = {
    def query(emd5: Column[String], targetAuth: Column[String]): Query[Column[String], String] =
      for {
        eh <- handlesFromMd5("email", emd5)
        p <- relatedPeople(eh)
        h <- relatedHandles(p)
        if h.auth === targetAuth && h.handle.isNotNull
      } yield h.handle

    Compiled(query _)
  }

  def emd52handlesViaPeople(emd5: String, targetAuth: String = "twitter"): List[String] = {
    db withSession {implicit session =>
      val q = emd52handlesViaPeopleCompiled(emd5, targetAuth)

      println(q.selectStatement)

      q.list()
    }
  }
}
