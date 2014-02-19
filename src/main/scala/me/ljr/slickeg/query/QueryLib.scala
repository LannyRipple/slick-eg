package me.ljr.slickeg.query

import me.ljr.slickeg.model.DAL

/**
 * Various subqueries that can be composed into larger queries for use in the API layer.
 *
 * Where to place queries is a bit fast and loose.  Ideally the queries will be small enough
 * to be related to a single primary table into which cake layer it should be placed.
 */
class QueryLib(protected val DAL: DAL) extends DALHolder
  with PeopleCake
  with HandlesCake
