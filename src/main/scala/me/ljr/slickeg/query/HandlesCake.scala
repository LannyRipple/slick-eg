package me.ljr.slickeg.query

import me.ljr.slickeg.model._

trait HandlesCake {
  self: DALHolder with PeopleCake =>

  import DAL.slickDriver.simple._
  import DAL._

  type HandlesQuery = Query[DAL#Handles, Handle]

  def handlesFromMd5(auth: Column[String], emd5: Column[String]): HandlesQuery =
    for {
      h <- handles if h.auth === auth && h.md5 === emd5
    } yield h

  def handlesFromHandle(auth: Column[String], handle: Column[String]): HandlesQuery =
    for {
      h <- handles if h.auth === auth && h.handle === handle
    } yield h

  def relatedPeople(h: DAL#Handles): PeopleQuery =
    for {
      ph <- peopleHandlesMap if ph.hid === h.id
      p <- ph.peopleFK
    } yield p

  def knownHandlesByAuth(h: DAL#Handles, auth: Column[String]): Column[Boolean] = {
    h.auth === auth && h.handle.isNotNull
  }
}
