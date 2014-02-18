package me.ljr.slickeg.query

import me.ljr.slickeg.model.DAL

class QueryLib(protected val DAL: DAL) extends DALHolder
  with HandlesCake
