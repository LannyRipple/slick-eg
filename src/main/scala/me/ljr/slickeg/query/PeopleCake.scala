package me.ljr.slickeg.query

import me.ljr.slickeg.model._

trait PeopleCake {
  self: DALHolder with HandlesCake =>

  import DAL.slickDriver.simple._
  import DAL._

  type PeopleQuery = Query[DAL#People, Person]

  def relatedHandles(p: DAL#People): HandlesQuery =
    for {
      ph <- peopleHandlesMap if ph.pid === p.id
      h <- ph.handlesFK
    } yield h

}
