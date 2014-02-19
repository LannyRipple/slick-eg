package me.ljr.slickeg.model

class PersonId(val id: Long) extends AnyVal

case class Person(id: PersonId, guid: String)
