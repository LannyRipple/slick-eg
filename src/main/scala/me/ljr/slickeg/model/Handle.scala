package me.ljr.slickeg.model

class HandleId(val id: Long) extends AnyVal

case class Handle(id: HandleId, auth: String, md5: String, handle: Option[String])
