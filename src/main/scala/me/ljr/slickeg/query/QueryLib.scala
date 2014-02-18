package me.ljr.slickeg.query

import me.ljr.slickeg.model.DAL

class QueryLib(val DAL: DAL) extends DALHolder
  with HandlesCake
