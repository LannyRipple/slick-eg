package me.ljr.slickeg.query

import me.ljr.slickeg.model._

trait HandlesCake {
  self: DALHolder =>

  import DAL._
  import DAL.profile.slickDriver.simple._

  type HandlesQuery = Query[DAL#Handles, Handle]

  def handlesFromMd5(auth: Column[String], emd5: Column[String]): HandlesQuery =
    for {
      h <- handles if h.auth === auth && h.md5 === emd5
    } yield h

  def handlesFromHandle(auth: Column[String], handle: Column[String]): HandlesQuery =
    for {
      h <- handles if h.auth === auth && h.handle === handle
    } yield h

  def relatedHandlesViaPeople(q: DAL#Handles): HandlesQuery =
    for {
      eph <- peopleHandlesMap if eph.hid === q.id
      hph <- peopleHandlesMap if hph.pid === eph.pid
      h <- handles if h.id === hph.hid
    } yield h

  def handlesByAuth(auth: Column[String])(h: DAL#Handles): Column[Boolean] = {
    h.auth === auth && h.handle.isNotNull
  }
}
