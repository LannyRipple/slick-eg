package me.ljr.slickeg

import org.specs2.mutable._
import me.ljr.slickeg.TestDB.API

class TestGeneric extends Specification {

  import TestDB.DAL.slickDriver.simple._
  import TestDB.DAL._

  "Slick DB" should {
    step {
      db withSession { implicit session =>
        TestDB.createSchema()
        TestDB.populateData()
      }
    }

    "handles from emd5s via people should work" in {
      val xs = db withSession { implicit session =>
        API.getHandlesFromEmd5ViaPeople("md5-lanny@spotright.com")
      }
      println(xs)
      ok
    }
  }

}
