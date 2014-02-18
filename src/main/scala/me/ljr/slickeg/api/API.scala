package me.ljr.slickeg.api

import scala.language.postfixOps

import me.ljr.slickeg.model._

class API(val DAL: DAL) {

  import DAL._
  import DAL.profile.slickDriver.simple._

  def handlesFromEmd5ViaPeople(emd5: Column[String])(implicit session: Session): Query[DAL#Handles, Handle] =
    for {
      eh <- handles if eh.md5 === emd5
      eph <- peopleHandlesMap if eph.hid === eh.id
      hph <- peopleHandlesMap if hph.pid === eph.pid
      h <- handles if h.id === hph.hid
    } yield h

  def handlesByAuth(auth: Column[String])(h: DAL#Handles) = {
    h.auth === auth && h.handle.isNotNull
  }

  def getHandlesFromEmd5ViaPeople(emd5: Column[String], auth: Column[String]): List[String] = {
    db() withSession {implicit session =>
      val q =
        handlesFromEmd5ViaPeople(emd5)
          .filter(handlesByAuth("twitter"))
          .map{_.handle}

      q.list()
    }
  }

}
