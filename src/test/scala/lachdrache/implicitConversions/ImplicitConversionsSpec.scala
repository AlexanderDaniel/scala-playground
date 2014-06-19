package lachdrache.implicitConversions

import org.scalatest.FunSpec

class ImplicitConversionsSpec extends FunSpec {

  case class LegacyClass()

  case class ScalaDays() {
    def summary: String = ":-)"
  }

  object ScalaDays {
    implicit def toScalaDays(legacyClass: LegacyClass): ScalaDays =
      ScalaDays()
  }

  describe("implicit conversion") {
    it("should allow summary to be called on LegacyClass") {
      assertResult(":-)") {
        import ScalaDays._
        LegacyClass().summary
      }
    }
  }
}