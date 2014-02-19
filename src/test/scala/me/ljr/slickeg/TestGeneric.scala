package me.ljr.slickeg

import org.specs2.mutable._
import me.ljr.slickeg.TestDB.API

class TestGeneric extends Specification {

  import TestDB.DAL.slickDriver.simple._

  lazy val db = {
    val db = TestDB.DAL.database
    db withSession { implicit session =>
      TestDB.createSchema()
      TestDB.populateData()
    }
    db
  }

  "Slick DB" should {

    "handles from emd5s via people should work" in {
      val xs = db withSession { implicit session =>
        API.emd52handlesViaPeople("md5-lanny@spotright.com")
      }
      println(xs)
      ok
    }
  }

}
