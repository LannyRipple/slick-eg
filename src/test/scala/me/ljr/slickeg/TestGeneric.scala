package me.ljr.slickeg

import org.specs2.mutable._
import me.ljr.slickeg.TestDB.API

class TestGeneric extends Specification {

  import TestDB.dbconf.slickDriver.simple._

  "Slick DB" should {
    step {
      TestDB.populateSchema(TestDB.API.DAL)
    }

    "handles from emd5s via people should work" in {
      val xs = API.getHandlesFromEmd5ViaPeople("md5-lanny@spotright.com")
      println(xs)
      ok
    }
  }

}
