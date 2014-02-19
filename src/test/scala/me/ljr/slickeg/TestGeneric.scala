package me.ljr.slickeg

import org.specs2.mutable._

import me.ljr.slickeg.TestDB.API

class TestGeneric extends Specification {

  val db = TestDB.DAL.database

  "Slick DB" should {

    step {
      db withSession {implicit session =>
        TestDB.createSchema()
        TestDB.populateData()
      }
    }

    "handles from emd5s via people should work" in {
      val xs = db withSession {implicit session =>
        API.emd52handlesViaPeople("md5-lanny@spotright.com", "twitter")
      }

      xs must contain("lannyripple")
      xs must have length 1
    }

    "get person by guid (plain SQL) should work" in {
      val xs = TestDB.DAL.jdbcDatabase withSession {implicit session =>
        API.getPersonByGuid.list("lanny")
      }

      xs must contain((1, "lanny"))
      xs must have length 1
    }
  }
}
